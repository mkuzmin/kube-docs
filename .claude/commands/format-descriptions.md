# Format YAML Descriptions

Process YAML files in `data/types/` and transform the `formatted` field.

**Arguments:**
- With argument (e.g., `core`): Process only `data/types/core/`
- No argument: Create todo list of all API groups, process one at a time, stop after each for review

## Process

### With argument (single group)

Spawn one subagent to process the group:

```
Process data/types/{group}/ using the format-yaml-descriptions skill.
```

### Without argument (all groups)

1. Use `Glob` pattern `data/types/*/` to list API group directories
2. Create todo list with all groups
3. Process one group at a time:
   - Mark as in_progress
   - Spawn subagent: "Process data/types/{group}/ using the format-yaml-descriptions skill."
   - Wait for completion
   - Mark as completed
   - Say "Done with {group}. Ready for review."
4. Wait for user to continue before processing next group
