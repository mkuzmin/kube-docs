---
name: yaml-formatter
description: Format YAML files in data/types/ by transforming the formatted field.
tools: Glob, Bash(yq:*), Task
permissionMode: bypassPermissions
---

# Format YAML Descriptions

Transform `description.formatted` fields in YAML files.

## Constraints

**MUST follow - these are the most common mistakes:**

- **Never rewrite text** - only remove filler at start, everything else stays verbatim
- **Never add formatting** - no extra blank lines, no restructuring paragraphs
- **Self-references use backticks** - only OTHER types get `[[wiki links]]`

## Architecture

**Why sub-agents:** Transformations require LLM judgment (identifying code patterns, type references, formatting enums). Each yq write includes LLM-generated text in the command string. With 100+ files, these tool calls would consume this agent's context. Sub-agents isolate this - only counts return here.

**Sequential processing:** Batches run one at a time. Progress reported after each batch completes.

## Tools

| Task | Tool |
|------|------|
| List files | `Glob` with `**/*.yaml` pattern |
| Process batch | `Task` with general-purpose sub-agent |

## Transformation Rules

Include these rules in sub-agent prompts:

### 1. Remove filler at start
Remove redundant names and filler verbs at the **start** of descriptions. Only remove text, never add or rewrite words elsewhere.

Filler verbs to remove: represents, specifies, contains, describes, holds, is.

- `_APIService.yaml`: `APIService represents a server for...` → `A server for...`
- `_ExpressionWarning.yaml`: `ExpressionWarning is a warning...` → `A warning...`
- `spec.yaml`: `Spec specifies information for locating...` → `Information for locating...`
- `status.yaml`: `Status contains derived information...` → `Derived information...`
- `_ServiceReference.yaml`: `ServiceReference holds a reference...` → `A reference...`
- `_APIServiceCondition.yaml`: `APIServiceCondition describes a condition...` → `A condition...`
- `ValidatingAdmissionPolicy/status.yaml`: `The status of the ValidatingAdmissionPolicy` → `The status`

**Do NOT rewrite sentences.** If original says "We'd recommend X", keep it - don't change to "Recommended: X". 

### 2. Code in backticks
Wrap in backticks anything that looks like code:
- Own field names (self-references): `VersionPriority`, `groupPriorityMinimum`
- Dotted paths: `spec.os.name`, `version.group`
- Version strings: `v1`, `v10beta3`, `v1.bar`
- Alphanumeric identifiers: `foo1`, `foo10`
- Technical terms in version context: `alpha`, `beta`, `GA`
- Regex patterns: `[A-Za-z0-9][-A-Za-z0-9_.]*`
- Template patterns: `{ValidatingAdmissionPolicy name}/{key}`

### 3. Code blocks in triple backticks
Wrap multi-line code examples (JSON, YAML) in fenced code blocks with language tag:

Before:
```
For example, set the selector as follows: "namespaceSelector": {
  "matchExpressions": [
    {
      "key": "runlevel",
      "operator": "NotIn",
      "values": ["0", "1"]
    }
  ]
}
```

After:
````
For example, set the selector as follows:

```json
"namespaceSelector": {
  "matchExpressions": [
    {
      "key": "runlevel",
      "operator": "NotIn",
      "values": ["0", "1"]
    }
  ]
}
```
````

### 4. Type references in prose
References to **other types** use wiki links `[[TypeName]]`:
- "present in a Container" → "present in a [[Container]]"

**Self-references use backticks, NOT wiki links:**
- In `_Pod.yaml`: "Pod is..." → "`Pod` is..." (not `[[Pod]]`)
- In `spec.yaml`: "the spec field" → "the `spec` field" (not `[[spec]]`)

### 5. Handle "Required."
If description ends with "Required." or has standalone "Required." line:
- Remove the "Required." text
- Add `required: true` property to the YAML file

### 6. Format enums
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
3. For each batch sequentially:
   - Spawn `Task` with:
     - `subagent_type`: `general-purpose`
     - `description`: `Format YAML batch N/M`
     - `prompt`: Include constraints, transformation rules, file list, and these instructions:
       ```
       For each file:
       1. Read: yq '.description.original' <file>
       2. Transform in stages:
          - Remove filler verbs at start
          - Wrap inline code in backticks
          - Wrap multi-line JSON/YAML in fenced code blocks
          - type references → [[TypeName]], self-refs stay backticks
          - remove "Required." text
          - format enums as bullet list
       3. Write final result:
          formatted='<final>' yq -i '.description.formatted = strenv(formatted) | .description.formatted style="literal"' <file>
       4. If had "Required.", also run: yq -i '.required = true' <file>
       5. If enum, also run: yq -i '.enum = true' <file>

       Report: "Done." or "Done. Errors: [list]"
       ```
4. Report summary:
   ```
   Done. 58/60 files processed (2 errors: file1.yaml, file2.yaml)
   ```