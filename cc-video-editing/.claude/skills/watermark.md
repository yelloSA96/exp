---
name: watermark
description: Overlay an image watermark on a video. Usage: /watermark [image path] [position] — e.g. /watermark logo.png top-right
---

Use the `ffmpeg-video-editor` agent to add a watermark to the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments. The first argument is the path to the watermark image. The second optional argument is a position:
- `top-left` → `overlay=10:10`
- `top-right` → `overlay=W-w-10:10`
- `bottom-left` → `overlay=10:H-h-10`
- `bottom-right` → `overlay=W-w-10:H-h-10` (default if not specified)
- `center` → `overlay=(W-w)/2:(H-h)/2`

If no image path is provided, ask the user for the watermark image path before proceeding. Re-encoding is required. Place output in `./output/` named `<basename>_watermarked.mp4`.
