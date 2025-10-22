# Task Breakdown with Task master
## Setting Up
```bash
# Initialize Project
npm init -y
# Install Task Master AI
npm install task-master-ai
# Setting up the taskmaster directory
task-master init
```

## Next Steps
```log
   │   Things you should do next:                                                                                      │
   │                                                                                                                   │
   │   1. Configure AI models (if needed) and add API keys to `.env`                                                   │
   │      ├─ Models: Use `task-master models` commands                                                                 │
   │      └─ Keys: Add provider API keys to .env (or inside the MCP config file i.e. .cursor/mcp.json)                 │
   │   2. Discuss your idea with AI and ask for a PRD, and save it to .taskmaster/docs/prd.txt                         │
   │      ├─ Simple projects: Use example_prd.txt template                                                             │
   │      └─ Complex systems: Use example_prd_rpg.txt template (for dependency-aware task graphs)                      │
   │   3. Ask Cursor Agent (or run CLI) to parse your PRD and generate initial tasks:                                  │
   │      └─ MCP Tool: parse_prd | CLI: task-master parse-prd .taskmaster/docs/prd.txt                                 │
   │   4. Ask Cursor to analyze the complexity of the tasks in your PRD using research                                 │
   │      └─ MCP Tool: analyze_project_complexity | CLI: task-master analyze-complexity                                │
   │   5. Ask Cursor to expand all of your tasks using the complexity analysis                                         │
   │   6. Ask Cursor to begin working on the next task                                                                 │
   │   7. Add new tasks anytime using the add-task command or MCP tool                                                 │
   │   8. Ask Cursor to set the status of one or many tasks/subtasks at a time. Use the task id from the task lists.   │
   │   9. Ask Cursor to update all tasks from a specific task id based on new learnings or pivots in your project.     │
   │   10. Ship it!                                                                                                    │
   │                                                                                                                   │
   │   * Review the README.md file to learn how to use other commands via Cursor Agent.                                │
   │   * Use the task-master command without arguments to see all available commands. 
```

