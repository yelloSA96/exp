# Plan: Lyrics-Style Word-by-Word Video Text Overlay

## Context
The user has a trimmed MP4 video and a phrase-level transcript (alternating M:SS timestamps and text lines).
They want words to pop up one at a time as spoken — like a modern lyric video (not a subtitle paragraph).
Building from scratch using Python + FFmpeg (script already implemented — see notes below).

---

## Current Implementation (Python)

**Script:** `overlay_lyrics.py` in project root — already written.

### How it works

1. **Transcript parsing** (`transcripts/<video-stem>.txt`)
   - Lines alternate: `M:SS` timestamp → phrase text
   - Skips `[Music]` / `[...]` markers and empty lines
   - Falls back to stem with `-trimmed` suffix stripped

2. **Word timing**
   - Per phrase: `duration = next_timestamp - this_timestamp`
   - Words distributed evenly: `word_duration = duration / word_count`
   - Produces `(word_start, word_end, word)` tuples

3. **ASS subtitle generation**
   - One `Dialogue` event per word
   - Style: Arial 90pt, white + black outline, bold, lower-third centered
   - `\fad(80,80)` fade per word for pop effect

4. **FFmpeg burn-in**
   ```bash
   ffmpeg -y -i <trimmed_video> -vf "ass=output/<stem>.ass" -c:a copy output/<stem>-lyrics.mp4
   ```

### CLI
```bash
python3 overlay_lyrics.py [--video PATH] [--offset SECONDS]
```

### Output
- `output/<stem>.ass` — generated subtitle file (inspectable)
- `output/<stem>-lyrics.mp4` — final video with burned-in lyrics

---

## Open Question: Python vs Bash

**User asked:** *"Are you not able to do this with FFmpeg directly instead of using Python?"*

**Answer:**
- FFmpeg **burns** subtitles trivially: `ffmpeg -i video.mp4 -vf "ass=subtitles.ass" output.mp4`
- FFmpeg **cannot generate** the word-by-word timing — that requires scripting logic:
  - Parse `M:SS` timestamps
  - Calculate phrase durations
  - Distribute words evenly per phrase
  - Emit one `Dialogue` line per word in ASS format
- Could be done in **bash** (using `awk` + `bc`), but would be verbose and brittle
- **Python is just a timing calculator** — FFmpeg does all the actual video work
- Pipeline: `transcript.txt → [Python] → subtitles.ass → [FFmpeg] → lyrics.mp4`

**User's comment to consider:** Rewriting in bash is possible but Python is the cleaner tool. User has not yet decided.

---

## Critical Files
| File | Status |
|------|--------|
| `overlay_lyrics.py` | Created |
| `_place_video_here/Better-character-quotes-by-marcus-aurelius-trimmed.mp4` | Exists |
| `transcripts/Better-character-quotes-by-marcus-aurelius.txt` | Exists |
| `output/` | Created at runtime |

---

## Verification
1. `python3 overlay_lyrics.py`
2. Inspect `output/*.ass` — verify word-level `Dialogue` events with correct timestamps
3. Open `output/*-lyrics.mp4` — confirm one word appears at a time, in sync with speech
4. If out of sync: `python3 overlay_lyrics.py --offset <seconds>`