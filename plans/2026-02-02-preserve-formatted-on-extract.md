# Preserve Formatted Field on Extract

## Goal

When running extract, preserve manually-edited `formatted` fields instead of overwriting them with the original OpenAPI description.

## Current State

- `writeType` and `writeField` use `file.writeText()` which completely replaces the file
- Both `original` and `formatted` are written from the OpenAPI description
- Running extract destroys any manual edits to `formatted` fields

## Target State

- Extract reads existing file before writing
- If `formatted` exists, preserve it
- Only `original` and structural fields are updated from OpenAPI

## Approach

Note: kaml library doesn't support controlling literal style (`|-`) for single-line values, so YAML files are written as strings using `buildString`. Reading with kaml works fine.

1. Update `writeType`: if file exists, read with kaml and use existing `description.formatted`; otherwise use `original`
2. Update `writeField`: if file exists, read with kaml and use existing `description.formatted`; otherwise use `original`

## Verification

1. Run `./gradlew :app:extractDescriptions`
2. Run `git diff data/types/`
3. Verify no changes to files (extract preserves existing content)

## Scope

- `app/src/extract.kt`: Read existing files, preserve `formatted` field when writing
