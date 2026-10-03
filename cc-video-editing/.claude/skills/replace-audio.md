---
name: replace-audio
description: Replace the audio track of a video with an external audio file. Usage: /replace-audio [audio file path]
---

Use the `ffmpeg-video-editor` agent to replace the audio track of the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as the path to the replacement audio file. If provided, replace the video's audio with that file — map the video stream from the original and audio from the replacement, re-encode audio to AAC if needed, copy the video stream. If no audio file path is provided, ask the user for the path before proceeding.

Place output in `./output/` named `<basename>_replaced_audio.mp4`.
