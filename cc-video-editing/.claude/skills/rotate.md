---
name: rotate
description: Rotate a video 90, 180, or 270 degrees, or flip it. Usage: /rotate [90 | 180 | 270 | flip | flop]
---

Use the `ffmpeg-video-editor` agent to rotate or flip the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as a rotation or flip direction:
- `90` or `90cw` — rotate 90 degrees clockwise (`transpose=1`)
- `90ccw` — rotate 90 degrees counter-clockwise (`transpose=2`)
- `180` — rotate 180 degrees
- `270` — rotate 270 degrees clockwise
- `flip` or `vflip` — flip vertically
- `flop` or `hflip` — flip horizontally

If no arguments are provided, ask the user which rotation or flip to apply. Re-encoding is required. Place output in `./output/` named `<basename>_rotated_<direction>.mp4`.
