# Format YAML Descriptions

Process YAML files in `data/types/` and transform the `formatted` field.

**Arguments:**
- With argument (e.g., `core`): Process only `data/types/core/`
- No argument: Create todo list of all API groups, process one at a time, stop after each for review

## Rules

### 1. Code in backticks
Wrap regex patterns, field paths, template patterns in backticks.

Examples:
- Regex: `[A-Za-z0-9][-A-Za-z0-9_.]*` → `` `[A-Za-z0-9][-A-Za-z0-9_.]*` ``
- Field path: `spec.os.name` → `` `spec.os.name` ``
- Template: `{ValidatingAdmissionPolicy name}/{key}` → `` `{ValidatingAdmissionPolicy name}/{key}` ``

### 2. Type references
- In prose: use wiki links `[[TypeName]]`
- In code context (dotted paths, etc.): just backticks

Examples:
- "present in a Container" → "present in a [[Container]]"
- "set in both SecurityContext and PodSecurityContext" → "set in both [[SecurityContext]] and [[PodSecurityContext]]"
- "Container.Resources.Requests" → `` `Container.Resources.Requests` `` (code context, no wiki link)

### 3. Remove standalone "Required."
Delete lines containing only "Required." - this info is in the `required:` YAML property.

Example:
```
The key must be unique.

Required.
```
→
```
The key must be unique.
```

### 4. Format enums
Add `enum: true` to YAML and transform the description into a list format.

Enum patterns:
- `Can be "X" or "Y"`
- `Can be X, Y, Z`
- `Valid values are`

Example:
```
Type of deployment. Can be "Recreate" or "RollingUpdate". Default is RollingUpdate.
```
→
```
Type of deployment.
- `Recreate`
- `RollingUpdate` (**default**)
```

## Process

1. List YAML files recursively in target directory (`data/types/{group}/{Type}/*.yaml`)
2. For each file, spawn a subagent (Task tool with `general-purpose` type) that:
   - Reads the file
   - Applies transformation rules to `formatted` field
   - Adds `enum: true` if enum pattern detected
   - Writes the modified file
3. Launch multiple subagents in parallel (batch of 5-10) to speed up processing
4. Check each Task result for errors; collect failed file paths
5. Report failed files at the end for manual review or retry

This keeps each file's content isolated in its own context window.

After processing a group, stop and wait for user to review changes before continuing to next group.