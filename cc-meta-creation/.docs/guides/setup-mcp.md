# Setup 
There are few things to setup to getting started with claude code interacting with meta cli.

## (1) Start Claude
open claude in the repository
`claude`

## (2) Add Meta MCP
`claude mcp add --transport http --client-id <META_APP_ID> meta-ads https://mcp.facebook.com/ads`

## (3) Prompt away
```txt
List my ad accounts.
Create a new traffic campaign in ad account <AD_ACCOUNT_ID>.
Draft an ad set for my <CAMPAIGN_ID> campaign that optimizes for link clicks.
Summarize the top-spending campaigns in <AD_ACCOUNT_ID> over the last 30 days.
```