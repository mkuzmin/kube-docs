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

For multiline code blocks (JSON, YAML examples), use triple backticks:
````
```json
{"key": "value"}
```
````

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

1. List files: `find data/types/{group} -name "*.yaml"`
2. Split into batches of 20 files
3. Spawn subagents in parallel (5-10 at a time), each processes its batch using `yq`
4. After group completes, say "Done. Ready for review." No statistics. Wait for user before continuing to next group.

### Subagent instructions

Each subagent receives a list of file paths and processes them using `yq`:

```bash
# For each file:
# 1. Extract original description
original=$(yq '.description.original' file.yaml)

# 2. Apply transformations to $original (rules 1-4 above)

# 3. Write to formatted
yq -i ".description.formatted = strenv(formatted)" file.yaml

# 4. Add enum flag if needed
yq -i '.enum = true' file.yaml
```

Use `strenv()` to preserve multiline strings when writing.