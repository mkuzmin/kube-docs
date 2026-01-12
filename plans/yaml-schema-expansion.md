# Expand YAML Schema to Include Type Structure

## Goal

Remove OpenAPI dependency from markdown generation by storing all type/field structure in YAML files.

## Current State

- `extract.kt`: Reads OpenAPI → writes YAML (description only)
- `main.kt`: Reads OpenAPI (structure) + YAML (descriptions) → generates Logseq

## Target State

- `extract.kt`: Reads OpenAPI → writes YAML (description + structure)
- `main.kt`: Reads YAML only → generates Logseq

## YAML Schema

### TypeDoc (`_TypeName.yaml`)

```yaml
description:
  original: |
    Deployment enables declarative updates for Pods and ReplicaSets.
  formatted: |
    Deployment enables declarative updates for Pods and ReplicaSets.
kind: true
```

Fields:
- `description` - original and formatted text
- `kind` - boolean, true if type has `x-kubernetes-group-version-kind`

### FieldDoc (`fieldName.yaml`)

```yaml
description:
  original: |
    Specification of the desired behavior.
  formatted: |
    Specification of the desired behavior.
type: DeploymentSpec
required: true
```

Fields:
- `description` - original and formatted text (nullable for fields without description)
- `type` - lowercase = primitive (`string`, `integer`, `boolean`), PascalCase = reference (`DeploymentSpec`)
- `collection` - optional: `array` or `map` (omit for single values)
- `required` - boolean, true if field is required

### Examples

**Simple primitive:**
```yaml
type: integer
```

**Reference:**
```yaml
type: DeploymentSpec
required: true
```

**Array of reference:**
```yaml
type: Container
collection: array
required: true
```

**Map of primitive:**
```yaml
type: string
collection: map
```

**Map of reference:**
```yaml
type: Quantity
collection: map
```

## Steps

Each step migrates one field from OpenAPI to YAML:
- Add field to extract (write to YAML)
- Update generate to read from YAML instead of OpenAPI
- Verify markdown output unchanged

1. Add `kind` to TypeDoc
2. Add `type` to FieldDoc (primitives only)
3. Add `type` for references
4. Add `collection: array`
5. Add `collection: map`
6. Add `required`
7. Remove OpenAPI from generate
