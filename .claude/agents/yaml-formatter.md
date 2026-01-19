---
name: yaml-formatter
description: Format YAML files in data/types/ by transforming the formatted field.
tools: Glob, Bash(yq:*), Task
permissionMode: bypassPermissions
---

# Format YAML Descriptions

Transform `description.formatted` fields in YAML files.

## Architecture

**Why sub-agents:** Transformations require LLM judgment (identifying code patterns, type references, formatting enums). Each yq write includes LLM-generated text in the command string. With 100+ files, these tool calls would consume this agent's context. Sub-agents isolate this - only "Done" returns here.

## Tools

| Task | Tool |
|------|------|
| List files | `Glob` with `**/*.yaml` pattern |
| Process batch | `Task` with general-purpose sub-agent |

## Transformation Rules

Include these rules in sub-agent prompts:

### 1. Remove redundant name from start
If description starts with the type or field name (derived from filename), remove it and capitalize the next word:
- Type `_ExpressionWarning.yaml`: `ExpressionWarning is a warning information` → `Warning information`
- Field `key.yaml`: `key specifies the audit annotation key` → `The audit annotation key`
- Field `name.yaml`: `name is the name of the resource` → `Name of the resource`

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
2. Split into batches of 20 files
3. For each batch, spawn `Task` with:
   - `subagent_type`: `general-purpose`
   - `description`: `Format YAML batch`
   - `prompt`: Include file list, transformation rules, and these instructions:
     ```
     For each file:
     1. Read with: yq '.description.original' <file>
     2. Apply transformation rules
     3. Write with: formatted='<result>' yq -i '.description.formatted = strenv(formatted)' <file>
     4. If enum, also run: yq -i '.enum = true' <file>

     Report only: "Done." or list of errors.
     ```
4. Launch batches in parallel (up to 5 concurrent sub-agents)
5. Report: "Done." (or errors if any)