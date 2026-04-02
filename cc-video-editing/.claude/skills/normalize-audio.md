---
name: normalize-audio
description: Normalize audio levels in a video to EBU R128 broadcast standard using loudnorm. Usage: /normalize-audio
---

Use the `ffmpeg-video-editor` agent to normalize the audio of the video.

User-provided arguments: `$ARGUMENTS`

Apply the `loudnorm` audio filter to normalize loudness to EBU R128 broadcast standard. This requires re-encoding the audio stream. Keep the video stream with `-c:v copy`. If the user specified a file in the arguments, use it; otherwise auto-detect from `_place_video_here/`.

Place output in `./output/` named `<basename>_normalized.<ext>`.
