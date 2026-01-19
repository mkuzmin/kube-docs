---
name: yaml-formatter
description: Format YAML files in data/types/ by transforming the formatted field.
tools: Glob, Bash
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

### 1. Code in backticks
Wrap regex patterns, field paths, template patterns:
- `[A-Za-z0-9][-A-Za-z0-9_.]*` → `` `[A-Za-z0-9][-A-Za-z0-9_.]*` ``
- `spec.os.name` → `` `spec.os.name` ``
- `{ValidatingAdmissionPolicy name}/{key}` → `` `{ValidatingAdmissionPolicy name}/{key}` ``

### 2. Type references in prose
Use wiki links `[[TypeName]]`:
- "present in a Container" → "present in a [[Container]]"
- In code context (dotted paths): just backticks, no wiki link

### 3. Remove standalone "Required."
Delete lines containing only "Required." - info is in `required:` property.

### 4. Format enums
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
2. For each file:
   - Read original: `yq '.description.original' file.yaml`
   - Apply transformation rules (LLM judgment)
   - Write formatted: `yq -i ".description.formatted = strenv(formatted)" file.yaml`
   - If enum: `yq -i '.enum = true' file.yaml`
3. Report completion