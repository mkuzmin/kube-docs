# Plan: Preserve External YAML Fields in Extraction

## Problem Statement

YAML files in `data/types/` contain three kinds of data:

1. **OpenAPI-derived** - extracted by Kotlin code from specs (e.g., `description.original`, `type`, `required.openapi`)
2. **AI-added** - detected by Claude Code slash commands from Kubernetes source code and descriptions (e.g., `enum`)
3. **Edited** - `description.formatted` refined by AI or manually

Re-running extraction overwrites files, losing AI-added and edited data.

## Goal

Modify only OpenAPI-derived properties, preserve everything else.

## Approach

Use `yq` CLI to update individual properties. yq preserves file structure, formatting, and unknown fields automatically.

**Why not kaml?** kaml doesn't support literal block style (`|-`) for single-line values. Our YAML files use `|-` consistently for all descriptions. kaml would convert single-line descriptions to quoted strings, causing noisy diffs.

## Changes

Modify `app/src/extract.kt`:

Use `mkdirs()` + `touch` + `yq -i` for all files. `yq -i` requires file to exist; `touch` needs parent dir.

**Trade-off:** Multiple yq calls per file is slower than current approach. Acceptable for correctness; can optimize later if needed.

**OpenAPI-derived fields to update:**
- Type files: `description.original`, `kind`
- Field files: `description.original`, `type`, `collection`, `required` block (for apiVersion/kind/metadata only)

### yq Expressions

**Description fields** (type files and field files with descriptions):
```bash
# Set original (always) - strenv preserves literal style on existing files
DESC="..." yq -i '.description.original = strenv(DESC) | .description.original style="literal"' file.yaml

# Set formatted only if missing (preserves edits, initializes new files)
DESC="..." yq -i '.description.formatted = (.description.formatted // strenv(DESC)) | .description.formatted style="literal"' file.yaml
```

Note: `style="literal"` is required - without it, new files get plain strings instead of `|-` blocks.

**Type-specific fields:**
```bash
# kind (boolean) - set if true, delete if false/null
yq -i '.kind = true' file.yaml
yq -i 'del(.kind)' file.yaml
```

**Field-specific fields:**
```bash
# type (always present)
yq -i '.type = "TypeName"' file.yaml

# collection (optional) - set or delete
yq -i '.collection = "array"' file.yaml
yq -i 'del(.collection)' file.yaml

# required block - set entire block or delete
yq -i '.required = {"openapi": true}' file.yaml
yq -i '.required = {"kind": true}' file.yaml
yq -i '.required = {"openapi": true, "kind": true}' file.yaml
yq -i 'del(.required)' file.yaml
```

### Implementation Notes

- `required.kind` applies only to apiVersion/kind/metadata fields on kind types
- `required.openapi` comes from OpenAPI `required` array
- Set entire `required` block at once (simpler than managing nested fields)
- Fields without descriptions (apiVersion, kind, metadata) skip the description yq calls entirely

## Verification

1. Add `enum: true` to a field file manually
2. Run `./gradlew :app:extractDescriptions`
3. Verify `enum: true` preserved
4. Verify `description.formatted` preserved
5. Verify literal block style (`|-`) preserved (even for single-line values)
