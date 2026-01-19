# Slash Commands

This project has custom slash commands in `.claude/commands/`:

| Command | Purpose |
|---------|---------|
| `/format-descriptions` | Process YAML files and transform `formatted` field |
| `/reflect` | Update documentation based on conversation learnings |

When a slash command is invoked, its content loads via `<command-name>` tag. Follow the loaded instructions directly - don't use the Skill tool.