---
name: yaml-formatter
description: Format YAML files in data/types/ by transforming the formatted field.
tools: Glob, Bash(yq:*)
permissionMode: bypassPermissions
---

# Format YAML Descriptions

Transform `description.formatted` fields in YAML files.

## Tools

| Task | Tool |
|------|------|
| List files | `Glob` with `**/*.yaml` pattern |
| Read content | `yq '.description.original'` |
| Write content | `yq -i '.description.formatted = strenv(formatted)'` |
| Add enum flag | `yq -i '.enum = true'` |

**DO NOT use `find` command for file listing.** Use Glob tool instead.

## Transformation Rules

### 1. Remove redundant field name from start
If description starts with the field name, remove it and capitalize the next word:
- `key specifies the audit annotation key` → `Specifies the audit annotation key`
- `name is the name of the resource` → `Name of the resource`

### 2. Code in backticks
Wrap regex patterns, field paths, template patterns:
- `[A-Za-z0-9][-A-Za-z0-9_.]*` → `` `[A-Za-z0-9][-A-Za-z0-9_.]*` ``
- `spec.os.name` → `` `spec.os.name` ``
- `{ValidatingAdmissionPolicy name}/{key}` → `` `{ValidatingAdmissionPolicy name}/{key}` ``

### 3. Type references in prose
Use wiki links `[[TypeName]]`:
- "present in a Container" → "present in a [[Container]]"
- In code context (dotted paths): just backticks, no wiki link

### 4. Remove standalone "Required."
Delete lines containing only "Required." - info is in `required:` property.

### 5. Format enums
Transform "Can be X or Y" or "allowed values are" to list format. Add `enum: true` to YAML.

Before:
```
Type of deployment. Can be "Recreate" or "RollingUpdate". Default is RollingUpdate.
```

After (in formatted field):
```
Type of deployment.
- `Recreate`
- `RollingUpdate` (**default**)
```

## Workflow

1. `Glob` pattern `data/types/{group}/**/*.yaml` to list files
2. Read originals in batch: `yq '.description.original' file1.yaml file2.yaml ...` (5 files per call)
3. Process files in parallel batches of 5:
   - For each batch, issue 5 Bash tool calls in a single message
   - Each call: `formatted='...' yq -i '.description.formatted = strenv(formatted)' file.yaml`
   - If enum, chain: `formatted='...' yq -i '.description.formatted = strenv(formatted)' file.yaml && yq -i '.enum = true' file.yaml`
4. Report: "Done." (or errors if any). No statistics, no file lists.