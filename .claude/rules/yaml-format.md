# YAML Metadata Format

Type and field metadata is stored in `data/types/` as YAML files.

## Directory Structure

```
data/types/{group}/{TypeName}/
  _TypeName.yaml      # Type metadata
  fieldName.yaml      # Field metadata (one per field)
```

## Type Files (`_TypeName.yaml`)

```yaml
description:
  original: |-
    Deployment enables declarative updates for Pods and ReplicaSets.
  formatted: |-
    Deployment enables declarative updates for Pods and ReplicaSets.
kind: true  # only for top-level resources
```

## Field Files (`fieldName.yaml`)

```yaml
description:
  original: |-
    Specification of the desired behavior.
  formatted: |-
    Specification of the desired behavior.
type: PodSpec
collection: array
required: true
```

## Literal Block Style

Use `|-` (literal with strip chomping) for description values:
- `|` keeps one trailing newline in parsed value
- `|-` strips trailing newlines (what we want)

```yaml
# good - no trailing newline in value
formatted: |-
  Description text here.

# bad - value includes trailing newline
formatted: |
  Description text here.
```

## Workflow

**Initial extraction:**
1. Extract reads OpenAPI → writes YAML
2. Edit `formatted` fields to enrich documentation
3. Generate reads `formatted` → produces markdown

**K8s version upgrade:**
1. Extract updates `original` field
2. Git diff shows upstream changes
3. 3-way merge upstream changes into `formatted`