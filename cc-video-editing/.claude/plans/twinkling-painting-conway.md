# Plan: Convert settings-feature_polished.mp4 to GIF

## Context
The user wants to convert `_place_video_here/settings-feature_polished.mp4` (1.6 MB) into an animated GIF. The project already has a `/gif` skill and `ffmpeg-video-editor` agent built for exactly this purpose.

## Approach

Use the existing `/gif` skill which delegates to the `ffmpeg-video-editor` agent.

### FFmpeg two-pass palette strategy (already defined in the skill):
1. **Pass 1** — generate an optimized color palette with `palettegen`
2. **Pass 2** — apply palette with `paletteuse` for color-accurate output

### Parameters
- **Input:** `_place_video_here/settings-feature_polished.mp4`
- **Time range:** full video (no trim needed unless user specifies)
- **Width:** 480px (default; can be adjusted)
- **Frame rate:** 10–15 fps (to keep file size manageable)
- **Output:** `output/settings-feature_polished.gif`

## Critical Files
- `.claude/skills/gif.md` — skill definition with two-pass palette instructions
- `.claude/agents/ffmpeg-video-editor.md` — agent that executes FFmpeg commands
- `_place_video_here/settings-feature_polished.mp4` — input file
- `output/` — output directory

## Execution Steps
1. Invoke the `ffmpeg-video-editor` agent
2. Probe the input file with `ffprobe` to get duration and dimensions
3. Run Pass 1: generate palette PNG via `palettegen`
4. Run Pass 2: encode GIF using `paletteuse`
5. Output to `output/settings-feature_polished.gif`

## Verification
- Confirm `output/settings-feature_polished.gif` exists and is non-zero size
- Open the GIF to verify animation plays correctly
