# OpenAPI Spec Structure

Location: `kubernetes/api/openapi-spec/v3/`

## API Endpoints

- `/api/` - Core API group (`core/v1`). Legacy endpoint for built-in resources like Pod, Service, ConfigMap.
- `/apis/` - All other API groups (apps, batch, networking.k8s.io, etc.)

API groups can have multiple versions (v1, v1beta1, v1alpha1). We process only the preferred stable version per group - typically `v1`, except `autoscaling` which uses `v2`.

File naming: `api__v1_openapi.json` (core) vs `apis__apps__v1_openapi.json` (other groups).

## File Types

1. **Index files** (no version in name) - list available versions, minimal schemas
   - `api_openapi.json` - index for core API
   - `apis__apps_openapi.json` - index for apps group

2. **Versioned files** (contain actual schemas)
   - `api__v1_openapi.json` - core v1 types
   - `apis__apps__v1_openapi.json` - apps v1 types

## JSON Structure

```
{
  "openapi": "3.0.0",
  "info": {...},
  "paths": {...},           // HTTP endpoints (ignore)
  "components": {
    "schemas": {...},       // DATA TYPES (what we want)
    "securitySchemes": {...}
  }
}
```

## Schema Keys

Fully qualified names: `io.k8s.api.core.v1.Pod`

## Schema Structure

```json
{
  "description": "Pod is a collection of containers...",
  "type": "object",
  "required": ["containers"],
  "properties": {
    "apiVersion": { "type": "string", "description": "..." },
    "metadata": { "$ref": "#/components/schemas/io.k8s.apimachinery.pkg.apis.meta.v1.ObjectMeta" }
  },
  "x-kubernetes-group-version-kind": [{ "group": "", "kind": "Pod", "version": "v1" }]
}
```

Treat documented fields as required (non-nullable). If a field is missing, fail with an error rather than using nullable types with fallbacks.

```kotlin
// good - fail if description is missing
data class Schema(
    val description: String,
)

// bad - unnecessary fallback hides data problems
data class Schema(
    val description: String? = null,
)
```

## Duplication Pattern

Each spec includes:
- **Own types** (e.g., `io.k8s.api.core.v1.*`) - unique to that API group
- **Shared types** (`io.k8s.apimachinery.pkg.*`) - duplicated across all specs

Example from `api__v1_openapi.json`: 246 total schemas, 216 are `io.k8s.api.core.*`, 22 are `io.k8s.apimachinery.*`.

## Processing Strategy

See `api-groups.csv` for the mapping of API groups to files and package prefixes.

- Process only the spec files explicitly listed here
- Process only one preferred version per API group
- Filter schemas by package prefix WITH version to extract only unique types from each file
- Example: `io.k8s.api.batch.v1.` filters to batch-specific types only
- Ignore `io.k8s.apimachinery.pkg.*` types (duplicated in all specs)

## Type Classification

**Top-level resources** (kinds) have `x-kubernetes-group-version-kind` field:
- Can be created via kubectl (Pod, Deployment, Job)
- Example: `Job`, `CronJob`, `Pod`

**Helper types** lack this field:
- Nested within top-level resources
- Example: `JobSpec`, `PodSpec`, `Container`

**List types** (`*List`) should be excluded:
- Never referenced from other types
- Example: `JobList`, `PodList`

## Property Reference Patterns

Properties reference other types in two ways:

**Direct `$ref`** (rare):
```json
"additionalItems": {
  "$ref": "#/components/schemas/...JSONSchemaPropsOrBool"
}
```

**Wrapped in `allOf`** (common):
```json
"spec": {
  "allOf": [{ "$ref": "#/components/schemas/...PodSpec" }],
  "default": {},
  "description": "..."
}
```

The `allOf` wrapper is used when additional fields like `default` or `description` are present. The `allOf` array always contains exactly one element.

## Descriptions

Descriptions contain markup (`\n`, URLs, etc.). Handle when rendering (see Logseq rules for multiline content).
