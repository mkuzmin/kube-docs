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
type: string
ref: DeploymentSpec
array: true
required: true
```

Fields:
- `description` - original and formatted text (nullable for fields without description)
- `type` - primitive type: `string`, `integer`, `boolean`, `object` (mutually exclusive with `ref`)
- `ref` - reference to another type (mutually exclusive with `type`)
- `array` - boolean, true if array of type/ref
- `required` - boolean, true if field is required

### Examples

**Simple primitive:**
```yaml
description:
  original: |
    Number of desired pods.
  formatted: |
    Number of desired pods.
type: integer
```

**Simple reference:**
```yaml
description:
  original: |
    Specification of the desired behavior.
  formatted: |
    Specification of the desired behavior.
ref: DeploymentSpec
required: true
```

**Array of primitive:**
```yaml
description:
  original: |
    Arguments to the entrypoint.
  formatted: |
    Arguments to the entrypoint.
type: string
array: true
```

**Array of reference:**
```yaml
description:
  original: |
    List of containers belonging to the pod.
  formatted: |
    List of containers belonging to the pod.
ref: Container
array: true
required: true
```

**Empty field (no description in OpenAPI):**
```yaml
type: string
```

## Implementation Steps

### Step 1: Update yaml.kt

Update data classes with new fields:

```kotlin
@Serializable
data class TypeDoc(
    val description: Description,
    val kind: Boolean = false,
)

@Serializable
data class FieldDoc(
    val description: Description? = null,
    val type: String? = null,
    val ref: String? = null,
    val array: Boolean = false,
    val required: Boolean = false,
)
```

### Step 2: Update extract.kt

Modify `writeType()` and `writeField()` to include new fields:

```kotlin
fun writeType(file: File, schema: Schema) {
    val doc = TypeDoc(
        description = Description(
            original = schema.description,
            formatted = schema.description,
        ),
        kind = schema.kind != null,
    )
    file.writeText(yaml.encodeToString(TypeDoc.serializer(), doc))
}

fun writeField(file: File, prop: Property, required: Boolean) {
    val (type, ref, array) = extractTypeInfo(prop)
    val doc = FieldDoc(
        description = prop.description?.let {
            Description(original = it, formatted = it)
        },
        type = type,
        ref = ref,
        array = array,
        required = required,
    )
    file.writeText(yaml.encodeToString(FieldDoc.serializer(), doc))
}

fun extractTypeInfo(prop: Property): Triple<String?, String?, Boolean> {
    // Returns (type, ref, array)
    when {
        prop.type == "array" -> {
            val items = prop.items ?: error("array without items")
            return when {
                items.type != null -> Triple(items.type, null, true)
                items.ref != null -> Triple(null, extractRefName(items.ref), true)
                items.allOf != null -> Triple(null, extractRefName(items.allOf.single().ref), true)
                else -> error("unknown array items: $items")
            }
        }
        prop.type != null -> return Triple(prop.type, null, false)
        prop.ref != null -> return Triple(null, extractRefName(prop.ref), false)
        prop.allOf != null -> return Triple(null, extractRefName(prop.allOf.single().ref), false)
        else -> error("unknown property: $prop")
    }
}

fun extractRefName(ref: String): String {
    return ref.removePrefix("#/components/schemas/").substringAfterLast(".")
}
```

### Step 3: Run extract

```bash
./gradlew :app:extractDescriptions
```

Verify output:
```bash
cat data/types/apps/Deployment/_Deployment.yaml
cat data/types/apps/Deployment/spec.yaml
```

### Step 4: Update main.kt

Remove OpenAPI dependency from `generatePages()`:

1. Read type metadata from `_TypeName.yaml`
2. List field files in type directory
3. Read field metadata from each `fieldName.yaml`
4. Format type string from `type`/`ref`/`array` fields

```kotlin
fun generatePages(group: ApiGroup, typesDir: File, outDir: File) {
    val helperTypesDir = File(outDir, "types").also { it.mkdirs() }
    val groupTypesDir = File(typesDir, group.group)

    groupTypesDir.listFiles()?.filter { it.isDirectory }?.forEach { typeDir ->
        val typeName = typeDir.name
        val typeDoc = readTypeDoc(File(typeDir, "_$typeName.yaml"))

        val dir = if (typeDoc.kind) outDir else helperTypesDir
        val filename = "${group.group}___${group.apiVersion}___$typeName.md"
        val file = File(dir, filename)

        val fields = typeDir.listFiles()
            ?.filter { it.extension == "yaml" && !it.name.startsWith("_") }
            ?.map { it.nameWithoutExtension to readFieldDoc(it) }
            ?: emptyList()

        file.writeText(buildString {
            // ... generate markdown from typeDoc and fields
        })
    }
}

fun formatType(field: FieldDoc, currentGroup: String): String {
    val baseType = when {
        field.type != null -> field.type
        field.ref != null -> {
            // Check if ref is in same group (would need more context)
            // For now, assume all refs are linkable
            "[[${field.ref}]]"
        }
        else -> "object"
    }
    return if (field.array) "[]$baseType" else baseType
}
```

### Step 5: Run generate and verify

```bash
./gradlew :app:run
```

Compare output with previous version to ensure no regressions.

### Step 6: Remove OpenAPI from generate

After verification, remove:
- `specDir` variable from `main()`
- `loadSchemas()` function (or move to extract-only)
- OpenAPI-related code from `main.kt`

## Verification

1. Extract generates correct YAML structure
2. Generate produces identical Logseq output
3. No OpenAPI files read during generate
