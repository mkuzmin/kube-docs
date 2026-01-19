# Format YAML Descriptions

Process YAML files in `data/types/` and transform the `formatted` field.

**Arguments:**
- With argument (e.g., `core`): Process only `data/types/core/`
- No argument: Create todo list of all API groups, process one at a time, stop after each for review

## Process

### With argument (single group)

Spawn the `yaml-formatter` subagent to process the group:

```
Process data/types/{group}/
```

### Without argument (all groups)

1. Use `Glob` pattern `data/types/*/` to list API group directories
2. Create todo list with all groups
3. Process one group at a time:
   - Mark as in_progress
   - Spawn `yaml-formatter` subagent: "Process data/types/{group}/"
   - Wait for completion
   - Mark as completed
   - Say "Done with {group}. Ready for review."
4. Wait for user to continue before processing next group
