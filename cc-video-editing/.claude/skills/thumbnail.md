---
name: thumbnail
description: Extract a still-frame thumbnail from a video at a specific timestamp. Usage: /thumbnail [timestamp] — e.g. /thumbnail 0:30
---

Use the `ffmpeg-video-editor` agent to extract a thumbnail image from the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as a timestamp (e.g., `0:30`, `1:15`, `90`). Extract a single frame at that position as a PNG using `-frames:v 1`.

If no timestamp is provided, ask the user at what point in the video they want the thumbnail. If the user says "best" or "auto", use FFmpeg's `thumbnail` filter to let FFmpeg select the most representative frame from the first few seconds.

Place output in `./output/` named `<basename>_thumb_<timestamp>.png`.
