---
name: meta-ads-campaigns
description: Manage Meta (Facebook/Instagram) ad campaigns, ad sets, and ads via the `meta` CLI (Ads CLI, from the meta-ads Python package). Use when the user asks to list, inspect, create, update, pause, resume, or delete a campaign, ad set, or ad in their Meta ad account. Not for insights/reporting, creatives, catalogs, or pixels/datasets — those are out of scope for this skill.
---

# Meta Ads Campaign Management

Wraps the `meta` CLI (Meta's official Ads CLI, package `meta-ads`, entry point `meta`) to
manage campaigns, ad sets, and ads in a Meta ad account.

## Scope

In scope: `meta ads campaign *`, `meta ads adset *`, `meta ads ad *` (list, get, create,
update, delete).

Out of scope — do not use this skill for insights/reporting, creatives, catalogs,
datasets/pixels, pages, or studies, even though the `meta` CLI supports them. If the user
asks for one of those, say it's outside this skill's current scope rather than improvising.

## Before doing anything

1. Confirm the CLI is authenticated: run `meta auth status`. If it reports not
   authenticated, tell the user to set the `ACCESS_TOKEN` (and usually `AD_ACCOUNT_ID`)
   environment variables themselves — do not ask them for the token value or try to set
   credentials on their behalf.
2. Run commands from a shell where the project's virtualenv is active (or otherwise where
   `meta` resolves), e.g. `source .venv/bin/activate`.
3. Use `meta ads <group> <command> --help` whenever you're unsure of a flag — the CLI's
   built-in help is authoritative and more detailed than this file; don't guess at flags.

## Guardrails

- **Reads run freely**: `list` and `get` for campaign/adset/ad can be run without asking
  first.
- **Writes require confirmation**: before running any `create`, `update`, or `delete`,
  show the user the exact command you're about to run (including all flags) and wait for
  their go-ahead. This applies even though the CLI has its own `delete` confirmation
  prompt — confirm with the user first regardless.
- **Never pass `--force` / `--no-input`** on a write command initiated by you. Those exist
  for unattended automation, not for this interactive skill.
- **Respect `--no-input` yourself only if the user explicitly asks for unattended/scripted
  behavior** — otherwise leave the CLI's interactive safety prompts intact.
- New campaigns default to `PAUSED` status unless the user explicitly asks for `ACTIVE`.
  Don't override that default.
- Budgets are in **cents** (e.g. `5000` = $50.00) — state the dollar equivalent back to the
  user when confirming a budget-related command so a units mistake is caught before it runs.
- Campaign budget mode (CBO / ABO / flex) is mutually exclusive — see
  `meta ads campaign create --help` for the rules before constructing a create command.
  Don't mix a campaign-level budget with ad-set-level budgets.

## Typical flow

1. `meta ads campaign list` (or `adset list <campaign_id>` / `ad list <adset_id>`) to find
   or confirm the target resource.
2. `meta ads <group> get <id>` to inspect details before an update/delete.
3. Construct the write command, show it to the user, get confirmation, then run it.
4. Report back the CLI's output (it defaults to a table; add `-o json` if the user wants
   structured output).

## Notes

- Full create/update/delete sequencing for a campaign → ad set → creative → ad is
  documented in `meta ads ad create --help`; creative creation itself is out of this
  skill's scope, so if a write needs a creative first, tell the user that step falls
  outside this skill.
- Reference: https://developers.facebook.com/documentation/ads-commerce/ads-ai-connectors/ads-cli/ads-cli-overview.md