# Plan: Create FFmpeg Claude Code Skills

## Context

This project uses a comprehensive `ffmpeg-video-editor` subagent (`.claude/agents/ffmpeg-video-editor.md`) that handles all FFmpeg operations. However, invoking it requires describing the operation in natural language each time.

The goal is to create **user-invokable Claude Code skill files** — thin, operation-specific slash commands stored in `.claude/skills/` — that users can trigger with `/skill-name [args]` (e.g., `/trim 0:30 1:45`). Each skill dispatches to the existing `ffmpeg-video-editor` agent with a clear, pre-structured prompt. No FFmpeg logic lives in the skill files themselves.

---

## Approach

Create `.claude/skills/` directory and 16 skill files covering every major FFmpeg operation category. Each skill file has:
- YAML frontmatter: `name`, `description`
- Prompt body that routes to `ffmpeg-video-editor` with explicit argument parsing instructions and a fallback to ask the user when arguments are missing

---

## Skills to Create

### Core Operations
| File | Slash Command | Purpose |
|------|--------------|---------|
| `trim.md` | `/trim 0:30 1:45` | Cut video to time range |
| `convert.md` | `/convert mp4` | Transcode to target format/codec |
| `concat.md` | `/concat clip1 clip2` | Merge multiple clips into one |

### Audio Operations
| File | Slash Command | Purpose |
|------|--------------|---------|
| `extract-audio.md` | `/extract-audio mp3` | Pull audio track out |
| `remove-audio.md` | `/remove-audio` | Strip audio, keep silent video |
| `replace-audio.md` | `/replace-audio music.mp3` | Swap audio track |
| `normalize-audio.md` | `/normalize-audio` | Loudnorm EBU R128 normalization |

### Visual Transforms
| File | Slash Command | Purpose |
|------|--------------|---------|
| `resize.md` | `/resize 720p` | Scale to resolution |
| `crop.md` | `/crop 9:16` | Crop to region or aspect ratio |
| `rotate.md` | `/rotate 90` | Rotate / flip orientation |
| `speed.md` | `/speed 2x` | Change playback speed |

### Effects
| File | Slash Command | Purpose |
|------|--------------|---------|
| `fade.md` | `/fade 1s 2s` | Add fade-in/out (video + audio) |
| `watermark.md` | `/watermark logo.png top-right` | Overlay image watermark |
| `subtitles.md` | `/subtitles subs.srt` | Burn subtitles (SRT or ASS) |

### Export
| File | Slash Command | Purpose |
|------|--------------|---------|
| `gif.md` | `/gif 0:10 0:15 480` | Export clip as palette-optimized GIF |
| `thumbnail.md` | `/thumbnail 0:30` | Extract single frame as PNG |

---

## Key Design Decisions

- **No FFmpeg logic in skills** — all command construction stays in `ffmpeg-video-editor.md`
- **Explicit argument parsing** — each skill tells the agent exactly what formats to accept
- **Ask-don't-guess** — all skills instruct the agent to ask for required info rather than proceed with defaults when arguments are missing
- **Agent routing** — every skill body says "Use the `ffmpeg-video-editor` agent" to make routing deterministic
- **Consistent output** — all skills point to `./output/` with the naming convention already established in the agent
- **`/fade` combines video + audio** — mirrors how the agent applies `fade` + `afade` together; no split skill needed

---

## Critical Files

- **Read before creating:** `.claude/agents/ffmpeg-video-editor.md` — to ensure skill prompts align with agent capabilities
- **Create (new directory):** `.claude/skills/`
- **Create (16 files):** `.claude/skills/trim.md`, `convert.md`, `concat.md`, `extract-audio.md`, `remove-audio.md`, `replace-audio.md`, `normalize-audio.md`, `resize.md`, `crop.md`, `rotate.md`, `speed.md`, `fade.md`, `watermark.md`, `subtitles.md`, `gif.md`, `thumbnail.md`

---

## Verification

1. **Invocation smoke test** — Type `/trim` with no args; Claude Code should recognize the skill and the agent should ask for start/end time
2. **Argument pass-through** — Place a short test video in `_place_video_here/`, run `/trim 0:05 0:15`, verify a 10-second clip appears in `./output/`
3. **Edge cases:**
   - `/speed 4x` — agent should chain two `atempo` filters (single filter max is 2.0)
   - `/concat` with no args — agent should scan `_place_video_here/` and ask user to confirm order
   - `/gif 0:05 0:10` — output should be palette-optimized, not naive conversion
4. **Output naming** — all outputs must follow `<basename>_<operation>.<ext>` pattern in `./output/`

---

## Results (completed 2026-04-02)

All 16 skill files created in `.claude/skills/`:

| File | Slash Command | Status |
|------|--------------|--------|
| `trim.md` | `/trim 0:30 1:45` | ✅ Created |
| `convert.md` | `/convert mp4` | ✅ Created |
| `concat.md` | `/concat clip1 clip2` | ✅ Created |
| `extract-audio.md` | `/extract-audio mp3` | ✅ Created |
| `remove-audio.md` | `/remove-audio` | ✅ Created |
| `replace-audio.md` | `/replace-audio music.mp3` | ✅ Created |
| `normalize-audio.md` | `/normalize-audio` | ✅ Created |
| `resize.md` | `/resize 720p` | ✅ Created |
| `crop.md` | `/crop 9:16` | ✅ Created |
| `rotate.md` | `/rotate 90` | ✅ Created |
| `speed.md` | `/speed 2x` | ✅ Created |
| `fade.md` | `/fade 1s 2s` | ✅ Created |
| `watermark.md` | `/watermark logo.png top-right` | ✅ Created |
| `subtitles.md` | `/subtitles subs.srt` | ✅ Created |
| `gif.md` | `/gif 0:10 0:15 480` | ✅ Created |
| `thumbnail.md` | `/thumbnail 0:30` | ✅ Created |

All skills dispatch to the `ffmpeg-video-editor` agent with explicit argument parsing. Missing arguments trigger a prompt to the user rather than guessing defaults.
