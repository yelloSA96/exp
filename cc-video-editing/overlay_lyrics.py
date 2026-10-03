#!/usr/bin/env python3
"""
overlay_lyrics.py — Burn word-by-word lyrics overlay onto a video (karaoke/lyric style).

Usage:
    python3 overlay_lyrics.py [--video PATH] [--offset SECONDS]
"""

import argparse
import glob
import os
import re
import subprocess
import sys


# ---------------------------------------------------------------------------
# Transcript parsing
# ---------------------------------------------------------------------------

def parse_timestamp(ts: str) -> float:
    """Convert M:SS or H:MM:SS to seconds."""
    parts = ts.strip().split(":")
    parts = [float(p) for p in parts]
    if len(parts) == 2:
        return parts[0] * 60 + parts[1]
    elif len(parts) == 3:
        return parts[0] * 3600 + parts[1] * 60 + parts[2]
    raise ValueError(f"Unrecognised timestamp: {ts!r}")


TIMESTAMP_RE = re.compile(r"^\d+:\d{2}$")
BRACKET_RE   = re.compile(r"^\[.*\]$")


def load_phrases(transcript_path: str) -> list[tuple[float, str]]:
    """Return list of (start_seconds, phrase_text) from transcript file."""
    with open(transcript_path, encoding="utf-8") as f:
        lines = [l.rstrip("\n") for l in f]

    phrases: list[tuple[float, str]] = []
    i = 0
    while i < len(lines):
        line = lines[i].strip()
        if TIMESTAMP_RE.match(line):
            ts = parse_timestamp(line)
            # Next line is the text
            if i + 1 < len(lines):
                text = lines[i + 1].strip()
                i += 2
                # Skip [Music] / [Applause] / other markers and empty lines
                if not text or BRACKET_RE.match(text):
                    continue
                phrases.append((ts, text))
            else:
                i += 1
        else:
            i += 1

    return phrases


# ---------------------------------------------------------------------------
# Word timing
# ---------------------------------------------------------------------------

def build_word_events(
    phrases: list[tuple[float, str]],
    video_duration: float,
    offset: float = 0.0,
) -> list[tuple[float, float, str]]:
    """
    Distribute words evenly within each phrase's time window.
    Returns list of (word_start, word_end, word).
    """
    events: list[tuple[float, float, str]] = []

    for idx, (start, text) in enumerate(phrases):
        start = start + offset
        # Phrase ends when next phrase begins (or video ends)
        if idx + 1 < len(phrases):
            phrase_end = phrases[idx + 1][0] + offset
        else:
            phrase_end = video_duration

        words = text.split()
        if not words:
            continue

        duration = max(phrase_end - start, 0.1)
        word_dur = duration / len(words)

        for w_idx, word in enumerate(words):
            w_start = start + w_idx * word_dur
            w_end   = w_start + word_dur
            events.append((w_start, w_end, word))

    return events


# ---------------------------------------------------------------------------
# ASS subtitle generation
# ---------------------------------------------------------------------------

def seconds_to_ass(t: float) -> str:
    """Convert seconds to ASS time format H:MM:SS.cc"""
    t = max(t, 0.0)
    hours   = int(t // 3600)
    minutes = int((t % 3600) // 60)
    secs    = int(t % 60)
    centis  = int(round((t - int(t)) * 100))
    if centis >= 100:
        centis = 99
    return f"{hours}:{minutes:02d}:{secs:02d}.{centis:02d}"


ASS_HEADER = """\
[Script Info]
ScriptType: v4.00+
PlayResX: 1920
PlayResY: 1080
WrapStyle: 0
ScaledBorderAndShadow: yes

[V4+ Styles]
Format: Name, Fontname, Fontsize, PrimaryColour, SecondaryColour, OutlineColour, BackColour, Bold, Italic, Underline, StrikeOut, ScaleX, ScaleY, Spacing, Angle, BorderStyle, Outline, Shadow, Alignment, MarginL, MarginR, MarginV, Encoding
Style: Lyrics,Arial,90,&H00FFFFFF,&H000000FF,&H00000000,&H00000000,1,0,0,0,100,100,2,0,1,4,2,2,60,60,80,1

[Events]
Format: Layer, Start, End, Style, Name, MarginL, MarginR, MarginV, Effect, Text
"""


def build_ass(events: list[tuple[float, float, str]]) -> str:
    lines = [ASS_HEADER]
    for w_start, w_end, word in events:
        start_str = seconds_to_ass(w_start)
        end_str   = seconds_to_ass(w_end)
        # \fad(fade_in_ms, fade_out_ms) for a quick pop effect
        text = r"{\fad(80,80)}" + word
        lines.append(
            f"Dialogue: 0,{start_str},{end_str},Lyrics,,0,0,0,,{text}"
        )
    return "\n".join(lines) + "\n"


# ---------------------------------------------------------------------------
# Video helpers
# ---------------------------------------------------------------------------

def get_video_duration(video_path: str) -> float:
    result = subprocess.run(
        [
            "ffprobe", "-v", "error",
            "-show_entries", "format=duration",
            "-of", "default=noprint_wrappers=1:nokey=1",
            video_path,
        ],
        capture_output=True,
        text=True,
        check=True,
    )
    return float(result.stdout.strip())


def auto_detect_video(directory: str = "_place_video_here") -> str:
    # Prefer trimmed versions
    trimmed = glob.glob(os.path.join(directory, "*-trimmed.mp4"))
    if trimmed:
        return sorted(trimmed)[0]
    any_mp4 = glob.glob(os.path.join(directory, "*.mp4"))
    if any_mp4:
        return sorted(any_mp4)[0]
    raise FileNotFoundError(f"No .mp4 files found in {directory!r}")


def find_transcript(video_path: str, transcript_dir: str = "transcripts") -> str:
    stem = os.path.splitext(os.path.basename(video_path))[0]
    # Try exact stem, then strip '-trimmed' suffix
    candidates = [stem, re.sub(r"-trimmed$", "", stem)]
    for name in candidates:
        path = os.path.join(transcript_dir, f"{name}.txt")
        if os.path.exists(path):
            return path
    raise FileNotFoundError(
        f"No transcript found for {stem!r} in {transcript_dir!r}. "
        f"Tried: {candidates}"
    )


# ---------------------------------------------------------------------------
# Main
# ---------------------------------------------------------------------------

def main() -> None:
    parser = argparse.ArgumentParser(description="Lyric-style word-by-word video overlay")
    parser.add_argument("--video",  help="Path to input video (auto-detected if omitted)")
    parser.add_argument("--offset", type=float, default=0.0,
                        help="Shift transcript timing by N seconds (+ = later, - = earlier)")
    args = parser.parse_args()

    # Resolve paths
    video_path = args.video or auto_detect_video()
    transcript_path = find_transcript(video_path)
    print(f"Video:      {video_path}")
    print(f"Transcript: {transcript_path}")

    # Stem for output files (strip -trimmed)
    stem = os.path.splitext(os.path.basename(video_path))[0]
    stem = re.sub(r"-trimmed$", "", stem)

    os.makedirs("output", exist_ok=True)
    ass_path   = os.path.join("output", f"{stem}.ass")
    lyrics_mp4 = os.path.join("output", f"{stem}-lyrics.mp4")

    # Build subtitle events
    print("Parsing transcript…")
    phrases = load_phrases(transcript_path)
    print(f"  {len(phrases)} phrases found")

    print("Getting video duration…")
    duration = get_video_duration(video_path)
    print(f"  {duration:.1f}s")

    events = build_word_events(phrases, duration, offset=args.offset)
    print(f"  {len(events)} word events generated")

    # Write ASS file
    ass_content = build_ass(events)
    with open(ass_path, "w", encoding="utf-8") as f:
        f.write(ass_content)
    print(f"ASS written → {ass_path}")

    # Burn subtitles with FFmpeg
    print("Running FFmpeg…")
    cmd = [
        "ffmpeg", "-y",
        "-i", video_path,
        "-vf", f"ass={ass_path}",
        "-c:a", "copy",
        lyrics_mp4,
    ]
    print(" ".join(cmd))
    subprocess.run(cmd, check=True)
    print(f"\nDone → {lyrics_mp4}")


if __name__ == "__main__":
    main()
