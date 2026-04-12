# Plan: Polish settings-feature.mov

## Context
The user has a raw Apple ReplayKit screen recording (`settings-feature.mov`) and wants it edited to "look good." The recording has several rough edges common to raw screen captures: non-standard resolution, variable frame rate, hard cuts at start/end, and a MOV container with limited compatibility. This plan applies a professional polish pipeline to produce a clean, shareable MP4.

## Source File Analysis
- **Path**: `_place_video_here/settings-feature.mov`
- **Duration**: 11.43 seconds
- **Resolution**: 2280 x 1202 (non-standard ~1.9:1 aspect ratio)
- **Frame rate**: 60fps nominal, ~34.5fps actual (variable frame rate — ReplayKit artifact)
- **Codec**: H.264 Main, BT.709
- **Audio**: None
- **Container**: MOV (QuickTime)

## Edits to Apply

| # | Operation | Why |
|---|---|---|
| 1 | `fps=30` | Normalize VFR → 30fps CFR; eliminates stuttering in players |
| 2 | `scale=1920:-2:flags=lanczos` | Downscale to 1920px wide, preserve aspect ratio (~1920x1012); Lanczos keeps text sharp |
| 3 | `pad=1920:1080:(ow-iw)/2:(oh-ih)/2:color=black` | Center in 1920x1080 canvas (34px black bars top/bottom) → standard 16:9 |
| 4 | `unsharp=luma_msize_x=5:luma_msize_y=5:luma_amount=0.8` | Recover sharpness lost in downscale; critical for UI text readability |
| 5 | `fade=t=in:st=0:d=0.5` | 0.5s fade in from black |
| 6 | `fade=t=out:st=10.93:d=0.5` | 0.5s fade to black at end (`11.43 - 0.5 = 10.93`) |

## FFmpeg Command

```bash
ffmpeg \
  -i _place_video_here/settings-feature.mov \
  -vf "fps=30,\
scale=1920:-2:flags=lanczos,\
pad=1920:1080:(ow-iw)/2:(oh-ih)/2:color=black,\
unsharp=luma_msize_x=5:luma_msize_y=5:luma_amount=0.8,\
fade=t=in:st=0:d=0.5,\
fade=t=out:st=10.93:d=0.5" \
  -c:v h264_videotoolbox \
  -q:v 65 \
  -movflags +faststart \
  -an \
  output/settings-feature_polished.mp4
```

## Output
- **Path**: `output/settings-feature_polished.mp4`
- **Resolution**: 1920x1080 (standard 16:9)
- **Frame rate**: 30fps constant
- **Format**: MP4 + faststart (web-compatible, instant playback in browsers/Slack/Notion)

## Execution
Delegate to the `ffmpeg-video-editor` agent (`.claude/agents/ffmpeg-video-editor.md`) with the command above. Permissions for `ffmpeg` and `ffprobe` are already granted in `.claude/settings.local.json`.

## Verification
After encoding, confirm with ffprobe:
```bash
ffprobe -v quiet -print_format json -show_streams output/settings-feature_polished.mp4
```
Expected: `width=1920`, `height=1080`, `avg_frame_rate="30/1"`, `codec_name="h264"`, duration ≈ 11.43s.

## Results (2026-04-03)

**Status: Completed successfully**

| Check | Expected | Actual | Status |
|---|---|---|---|
| Width | 1920 | 1920 | PASS |
| Height | 1080 | 1080 | PASS |
| avg_frame_rate | 30/1 | 30/1 | PASS |
| Codec | h264 | h264 (High profile) | PASS |
| Duration | ~11.43s | 11.53s | PASS* |

*0.1s overage is normal — `fps=30` pads to the next full frame boundary (346 frames / 30fps = 11.533s). Fade-out is fully contained within.

**Output**: `output/settings-feature_polished.mp4`
- File size: 1.57 MB (down from 3.64 MB source — 57% reduction)
- Bitrate: ~1,142 kb/s
- Encode time: ~1.6 seconds at 7.3x real-time (Apple VideoToolbox hardware)

---

## GIF Export (2026-04-03)

Converted `output/settings-feature_polished.mp4` → `output/settings-feature_polished.gif` using a two-pass palette approach (`palettegen` with `stats_mode=diff` + `paletteuse` with Bayer dithering).

| Property | Source MP4 | Output GIF |
|---|---|---|
| Duration | 11.53s | 11.50s |
| Resolution | 1920×1080 | 480×270 |
| Frame rate | 30 fps | 12 fps |
| File size | 1.57 MB | 548 KB |
| Codec | H.264 | GIF (pal8) |

**Status: Completed successfully** — 548 KB output, ~35% of the MP4 source size.
