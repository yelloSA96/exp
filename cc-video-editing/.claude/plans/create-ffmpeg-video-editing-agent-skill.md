# Plan: Create FFmpeg Video Editor Agent Skill

## Context

The user wants to create a new Claude Code agent skill based on the FFmpeg capabilities overview (transcoding, trimming, filters, concatenation, audio processing, etc.). The project already has two agents (`transcript-analyzer` and `video-snippet-extractor`) that follow a well-established format. This new agent will complement them by handling direct video editing operations via FFmpeg commands.

## What to Create

**New file:** `.claude/agents/ffmpeg-video-editor.md`
**New directory:** `.claude/agent-memory/ffmpeg-video-editor/` (with empty `MEMORY.md`)

## Agent Design

### Frontmatter
```yaml
name: ffmpeg-video-editor
model: sonnet
color: purple
memory: project
```

### Trigger / Description
Activate when the user wants to perform a specific video editing operation on a file — trimming, cutting, converting, merging, adding effects, adjusting audio, overlaying images/text, changing speed, or any other FFmpeg-executable transformation.

### Agent Responsibilities
1. **Understand the request**: Parse the editing operation(s) needed
2. **Inspect the input**: Use `ffprobe` to check video metadata (codec, resolution, duration, fps, audio streams) before building commands
3. **Construct the FFmpeg command**: Translate the user's intent into correct FFmpeg flags, filters, and options — choosing stream copy (`-c copy`) when re-encoding is unnecessary for speed
4. **Execute**: Run the command, placing output in `./output/`
5. **Verify**: Run `ffprobe` on the output to confirm success
6. **Report**: Summarize what was done, the output file path, and any relevant stats

### Capabilities to cover (from the FFmpeg description)
- Trimming / cutting (`-ss`, `-to`, `-t`)
- Format conversion / transcoding (codec selection)
- Concatenation (concat demuxer or filter)
- Video filters: scale, crop, pad, rotate, flip, speed (`setpts`), fade, overlay, watermark
- Audio: extract, merge, normalize volume, fade, sync
- Subtitle / text burn-in
- Frame extraction / image sequences
- Hardware acceleration hints (Apple VideoToolbox on macOS)

### Constraints / Guidelines
- Always auto-detect video from `_place_video_here/` if no path given (reuse pattern from `overlay_lyrics.py`)
- Output files go to `./output/` with descriptive names
- Use stream copy (`-c copy`) whenever no re-encoding is needed
- Confirm destructive or slow operations (e.g. full re-encode) before running
- On error, show the ffmpeg stderr and suggest a fix

## Critical Files

| File | Purpose |
|---|---|
| `.claude/agents/transcript-analyzer.md` | Reference for agent format and memory block boilerplate |
| `.claude/agents/video-snippet-extractor.md` | Reference for agent format |
| `.claude/settings.local.json` | Already allows `Bash(ffmpeg:*)` and `Bash(ffprobe:*)` |
| `overlay_lyrics.py` | Reference for auto-detect video / transcript patterns |
| `.claude/agent-memory/ffmpeg-video-editor/` | To be created (empty MEMORY.md) |

## Verification

1. Open Claude Code and type a command like: *"trim the video to 0:30–1:45"*
2. Confirm the `ffmpeg-video-editor` agent is invoked automatically
3. Verify `ffprobe` is called first, then the correct `ffmpeg` command runs
4. Check that output lands in `./output/`
