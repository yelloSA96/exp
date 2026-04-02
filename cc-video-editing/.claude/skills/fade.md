---
name: fade
description: Add fade-in and/or fade-out effects to a video (both video and audio). Usage: /fade [in duration] [out duration] — e.g. /fade 1s 2s
---

Use the `ffmpeg-video-editor` agent to add fade effects to the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as fade durations:
- Two values (e.g., `1s 2s` or `1 2`) — fade-in of the first duration, fade-out of the second
- One value — apply the same duration for both fade-in and fade-out

If no arguments are provided, ask whether the user wants fade-in, fade-out, or both, and the duration for each.

Apply both video (`fade`) and audio (`afade`) filters together. Re-encoding is required. Place output in `./output/` named `<basename>_faded.mp4`.
