# Plan: Preserve External YAML Fields in Extraction

## Current Problem

YAML files in `data/types/` contain three kinds of data:

1. **OpenAPI-derived** - extracted by Kotlin code from specs (e.g., `description.original`, `type`, `required`)
2. **AI-added** - detected by subagents from Kubernetes source code (e.g., `requiredImplicitly`, `enum`)
3. **Edited** - `description.formatted` refined by AI or manually

Current `extract.kt` completely overwrites YAML files, preserving only `description.formatted`. All other externally-added fields are lost.

When extraction runs after AI agents have enriched files, their work is destroyed.

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
- Field files: `description.original`, `type`, `collection`, `required`, `requiredImplicitly` (for apiVersion/kind/metadata only)

**Non-obvious points:**
- `requiredImplicitly` for apiVersion/kind/metadata: keep writing from extraction (current behavior). AI agents may add it to other fields.
- For optional fields (e.g., `collection`): use conditional in Kotlin - if value non-null call `yq '.field = strenv(VAL)'`, else call `yq 'del(.field)'`.
- Pass multiline strings via environment variable + `strenv()`: `DESC="..." yq -i '.desc = strenv(DESC)' file.yaml`. Avoids escaping issues, outputs literal block style.
- For booleans (e.g., `kind`): use literal `yq '.kind = true'` - no strenv needed.

## Verification

1. Add `enum: true` to a field file manually
2. Run `./gradlew :app:extractDescriptions`
3. Verify `enum: true` preserved
4. Verify `description.formatted` preserved
5. Verify literal block style (`|-`) preserved (even for single-line values)
