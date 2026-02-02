# Implicit Required Fields for Kinds

## Goal

Mark `apiVersion`, `kind`, and `metadata` as required for top-level resources (kinds) in generated markdown output.

## Current State

- **Extract** (`extract.kt`): Writes `required: true` only if field is in OpenAPI `required` array
- **Render** (`main.kt`): Shows `**required**` marker only if `field?.required == true`
- **Result**: apiVersion/kind/metadata appear without required marker, even though they're always required for kinds

## Target State

- Extraction sets `requiredImplicitly: true` on apiVersion/kind/metadata for types where `kind: true`
- Rendering checks both `required` and `requiredImplicitly` for the `**required**` marker

## Evidence

The Kubernetes Go code enforces these fields at runtime, even though they're marked `+optional` in struct tags.

**Kind and apiVersion are validated by serializers:**

`staging/src/k8s.io/apimachinery/pkg/runtime/serializer/json/json.go:196-201`:
```go
if len(actual.Kind) == 0 {
    return nil, actual, runtime.NewMissingKindErr(string(originalData))
}
if len(actual.Version) == 0 {
    return nil, actual, runtime.NewMissingVersionErr(string(originalData))
}
```

**metadata.name is required by validation:**

`staging/src/k8s.io/apimachinery/pkg/api/validation/objectmeta.go:166-167`:
```go
if len(meta.GetName()) == 0 {
    allErrs = append(allErrs, field.Required(fldPath.Child("name"), "name or generateName is required"))
}
```

Note: `metadata` itself isn't rejected at serialization (unlike kind/apiVersion), but validation requires `metadata.name`, making metadata effectively required.

## Approach

Store implicit requirement in YAML data, not in rendering logic.

1. Add `requiredImplicitly` property to field YAML format
2. Update extract to set `requiredImplicitly: true` for apiVersion/kind/metadata on kinds
3. Update render to check both `required` and `requiredImplicitly`

## Verification

1. Run `./gradlew :app:run`
2. Check a generated kind file (e.g., `core___v1___Pod.md`)
3. Verify apiVersion, kind, metadata lines include `**required**`

## Scope

- `app/src/yaml.kt`: Add `requiredImplicitly` field to `FieldMetadata` data class
- `app/src/extract.kt`: Set `requiredImplicitly: true` for apiVersion/kind/metadata on kinds
- `app/src/main.kt`: Check `requiredImplicitly` in required marker logic
- `data/types/`: YAML files updated by re-running extract
- `data/logseq/pages/generated/`: Markdown files updated by re-running render
- `.claude/rules/yaml-format.md`: Document `requiredImplicitly` field
