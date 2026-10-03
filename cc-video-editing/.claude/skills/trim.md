---
name: trim
description: Trim a video to a specific time range. Usage: /trim [start] [end] — e.g. /trim 0:30 1:45
---

Use the `ffmpeg-video-editor` agent to trim the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as a start time and end time (accept any format: `0:30`, `00:30`, `1:45`, `00:01:45`, or plain seconds like `90`). If both times are provided, trim from start to end. If only one time is provided, ask the user whether it is the start or end point. If no arguments are provided, ask the user for the start time and end time before proceeding.

Prefer `-c copy` stream copy when possible for speed. Place output in `./output/` named `<basename>_trimmed_<start>-<end>.mp4`.
