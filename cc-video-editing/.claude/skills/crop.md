---
name: crop
description: Crop a video to a specific region or aspect ratio. Usage: /crop [WxH] or /crop [W:H:X:Y] or /crop [aspect ratio like 9:16]
---

Use the `ffmpeg-video-editor` agent to crop the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as a crop specification. Accept these forms:
- `W:H:X:Y` — explicit width, height, and top-left position in pixels
- `WxH` — crop to WxH centered
- `9:16`, `1:1`, `16:9` — crop to aspect ratio (centered)

If no arguments are provided, ask the user what crop region or aspect ratio they want. Re-encoding is required. Place output in `./output/` named `<basename>_cropped.mp4`.
