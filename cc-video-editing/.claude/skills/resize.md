---
name: resize
description: Resize (scale) a video to a target resolution. Usage: /resize [resolution] — e.g. /resize 1920x1080 or /resize 720p
---

Use the `ffmpeg-video-editor` agent to resize the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as a target resolution. Accept these forms:
- `1920x1080` or `1920:1080` — explicit width x height
- `720p`, `1080p`, `4k` — shorthand (map to widths 1280, 1920, 3840 with `-2` height to preserve aspect ratio)
- `50%` — scale by percentage

Use the `scale` video filter. If no resolution is provided, ask the user for the target resolution before proceeding. Re-encoding is required. Place output in `./output/` named `<basename>_<resolution>.mp4`.
