---
name: gif
description: Convert a video clip to an optimized animated GIF. Usage: /gif [start] [end] [width] — e.g. /gif 0:10 0:15 480
---

Use the `ffmpeg-video-editor` agent to convert the video (or a clip of it) to an animated GIF.

User-provided arguments: `$ARGUMENTS`

Parse the arguments:
- First and second values (if present) are start/end times for the clip to convert
- Third value (optional) is the output width in pixels; default to 480 if not specified

Use a two-pass palette approach for quality: first generate a palette with `palettegen`, then apply it with `paletteuse` for a color-accurate, optimized GIF. Limit frame rate to 10–15fps to manage file size.

If no arguments are provided, ask for the time range and desired width before proceeding. Place output in `./output/` named `<basename>_<start>-<end>.gif`.
