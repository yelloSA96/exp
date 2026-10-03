---
name: remove-audio
description: Remove the audio track from a video, producing a silent video file. Usage: /remove-audio
---

Use the `ffmpeg-video-editor` agent to remove the audio track from the video.

User-provided arguments: `$ARGUMENTS`

Strip all audio streams using `-an`, keeping the video stream with `-c:v copy` for a fast, lossless operation. If the user specified a file path in the arguments, use that file; otherwise auto-detect from `_place_video_here/`.

Place output in `./output/` named `<basename>_no_audio.<ext>`.
