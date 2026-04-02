---
name: convert
description: Convert a video to a different format or codec. Usage: /convert [target format] — e.g. /convert mp4
---

Use the `ffmpeg-video-editor` agent to convert the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as the target format or codec (e.g., `mp4`, `mov`, `mkv`, `webm`, `h264`, `hevc`). Convert using stream copy where the container allows it, or re-encode when necessary. If no arguments are provided, ask the user what format or codec they want before proceeding.

For MP4 outputs, add `-movflags +faststart` for streaming compatibility. Use Apple VideoToolbox hardware acceleration on macOS when re-encoding H.264. Place output in `./output/` named `<basename>_converted.<ext>`.
