# Consolidate Required Fields Implementation Plan

## Overview

Consolidate the separate `required` and `requiredImplicitly` boolean fields into a single nested `required` object that explicitly tracks the source of the requirement.

**Reason:** This creates a baseline structure for tracking multiple sources of why a field is required. Future PRs will add other cases like fields implicitly required by validation rules.

## Current State

- `yaml.kt`: Two flat boolean fields in `FieldYaml`:
  ```kotlin
  val required: Boolean? = null,
  val requiredImplicitly: Boolean? = null,
  ```
- `extract.kt`: Writes them as separate lines:
  ```yaml
  required: true
  requiredImplicitly: true
  ```
- `main.kt`: Checks both fields: `field?.required == true || field?.requiredImplicitly == true`

## Desired End State

Single nested structure in YAML files:
```yaml
required:
  openapi: true  # from OpenAPI required array
  kind: true     # implicitly required for kinds (apiVersion/kind/metadata)
```

The `required:` block is omitted entirely when neither source applies (field is optional).

**Verification:** After running extract and render:
1. Compilation and `./gradlew :app:run` pass (deserialization will fail if any files have old format)
2. No changes in generated markdown files

## What We're NOT Doing

- Not changing which fields are marked as required
- Not adding new sources of required information
- Not changing the markdown output format

---

## Phase 1: Delete Old Required Data

### Overview
Remove all `required` and `requiredImplicitly` lines from existing YAML files. The extraction will regenerate this data in the new format.

### Changes Required:

Run from project root:
```bash
find data/types -name '*.yaml' -exec yq -i 'del(.required) | del(.requiredImplicitly)' {} \;
```

Note: Use `\;` not `+` because `yq -i` (in-place edit) doesn't work correctly with batched files.

### Success Criteria:

- `grep -r "^required:" data/types/` returns empty
- `grep -r "^requiredImplicitly:" data/types/` returns empty

---

## Phase 2: Update Kotlin Code

### Overview
Update all three Kotlin files to use the new nested format.

### Changes Required:

- `yaml.kt`: Replace `required: Boolean?` and `requiredImplicitly: Boolean?` with nested `required: Required?` class
- `extract.kt`: Write `required:` block with nested `openapi:` and `kind:` keys
- `main.kt`: Check `field?.required != null`

### Success Criteria:

- `./gradlew check` passes

---

## Phase 3: Regenerate Data

### Overview
Re-run extraction to add `required` blocks in the new format.

### Changes Required:

```bash
./gradlew :app:extractDescriptions
./gradlew :app:run
```

### Success Criteria:

- Both commands complete successfully
- No changes in generated markdown files

---

## Phase 4: Update Documentation

### Overview
Update `yaml-format.md` to document the new structure.

### Changes Required:

**File**: `.claude/rules/yaml-format.md`

- Replace `required: true` / `requiredImplicitly: true` example with nested `required:` block
- Document that `required:` block is omitted when field is not required
