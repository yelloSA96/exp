---
name: speed
description: Change the playback speed of a video. Usage: /speed [multiplier] — e.g. /speed 2x or /speed 0.5
---

Use the `ffmpeg-video-editor` agent to change the playback speed of the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as a speed multiplier (e.g., `2`, `2x`, `0.5`, `0.5x`). Values greater than 1 speed up; values less than 1 slow down. Apply both `setpts` for video and `atempo` for audio. If the speed factor is outside the `atempo` range of 0.5–2.0, chain multiple `atempo` filters (e.g., `4x` = `atempo=2.0,atempo=2.0`).

If no multiplier is provided, ask the user for the desired speed before proceeding. Place output in `./output/` named `<basename>_<speed>x.mp4`.
