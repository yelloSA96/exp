---
name: concat
description: Concatenate (merge) multiple video clips into one. Usage: /concat [file1] [file2] ... or just /concat to be guided
---

Use the `ffmpeg-video-editor` agent to concatenate video clips.

User-provided arguments: `$ARGUMENTS`

If file paths are provided in the arguments, merge those clips in the order listed. If no arguments are provided, ask the user which files to concatenate and in what order — also check `_place_video_here/` for available files.

Use the concat demuxer with `-c copy` if all clips share the same codec and resolution. Use the concat filter with re-encoding if they differ. Place output in `./output/` named `merged_<N>clips.mp4`.
