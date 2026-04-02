---
name: extract-audio
description: Extract the audio track from a video as a standalone audio file. Usage: /extract-audio [format] — e.g. /extract-audio mp3
---

Use the `ffmpeg-video-editor` agent to extract the audio from the video.

User-provided arguments: `$ARGUMENTS`

If a target audio format is specified (e.g., `mp3`, `aac`, `wav`, `flac`), extract to that format. If no format is specified, default to MP3 at high quality (`-q:a 2`). Strip the video stream entirely (`-vn`). If auto-detecting the input video from `_place_video_here/`, do so automatically.

Place the audio file in `./output/` named `<basename>_audio.<ext>`.
