---
name: subtitles
description: Burn subtitles from an SRT or ASS file into a video. Usage: /subtitles [subtitle file path]
---

Use the `ffmpeg-video-editor` agent to burn subtitles into the video.

User-provided arguments: `$ARGUMENTS`

Parse the arguments as the path to a subtitle file (`.srt` or `.ass`). If an SRT file is provided, use the `subtitles` video filter. If an ASS file is provided, use the `ass` filter.

If no subtitle file path is provided, scan for `.srt` or `.ass` files in the project root and `./transcripts/` — if found, ask the user to confirm which to use. If none are found, ask the user to provide the subtitle file.

Re-encoding is required for subtitle burn-in. Place output in `./output/` named `<basename>_subtitled.mp4`.
