package app

import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val apiGroups = loadApiGroups("../api-groups.csv")
    val specDir = File("../data/openapi")
    val outDir = File("../data/types")

    fun loadSchemas(group: ApiGroup): Map<String, Schema> {
        val json = Json { ignoreUnknownKeys = true }
        val spec = json.decodeFromString<OpenApiSpec>(File(specDir, group.file).readText())
        return spec.components.schemas
            .filterKeys { it.startsWith(group.packagePrefix) }
            .filterKeys { !it.endsWith("List") }
            .mapKeys { (key, _) -> key.removePrefix(group.packagePrefix) }
    }

    apiGroups.filter { it.preferredVersion }.forEach { group ->
        val schemas = loadSchemas(group)

        schemas.forEach { (typeName, schema) ->
            val typeDir = File(outDir, "${group.group}/$typeName")
                .also { it.mkdirs() }

            writeType(File(typeDir, "_$typeName.yaml"), schema)

            schema.properties.forEach { (fieldName, prop) ->
                val isRequired = fieldName in schema.required
                val skipDescription = fieldName in setOf("apiVersion", "kind", "metadata")
                writeField(File(typeDir, "$fieldName.yaml"), prop, isRequired, skipDescription)
            }
        }
    }
}

fun writeType(file: File, schema: Schema) {
    val formatted = if (file.exists()) {
        val y = yaml.decodeFromString(TypeYaml.serializer(), file.readText())
        y.description.formatted
    } else {
        schema.description
    }

    file.writeText(buildString {
        appendLine("description:")
        appendLiteralBlock("original", schema.description, indent = 2)
        appendLiteralBlock("formatted", formatted, indent = 2)
        if (schema.kind != null)
            appendLine("kind: true")
    })
}

fun writeField(file: File, prop: Property, isRequired: Boolean, skipDescription: Boolean) {
    val (type, collection) = extractType(prop)
    val formatted = if (file.exists() && prop.description != null) {
        val y = yaml.decodeFromString(FieldYaml.serializer(), file.readText())
        y.description?.formatted
    } else {
        null
    }

    file.writeText(buildString {
        if (!skipDescription && prop.description != null) {
            appendLine("description:")
            appendLiteralBlock("original", prop.description, indent = 2)
            appendLiteralBlock("formatted", formatted ?: prop.description, indent = 2)
        }
        appendLine("type: $type")
        if (collection != null) appendLine("collection: $collection")
        if (isRequired) appendLine("required: true")
    })
}

// kaml doesn't support literal style (`|-`) for single-line values, so we write YAML manually
fun StringBuilder.appendLiteralBlock(key: String, value: String, indent: Int) {
    val prefix = " ".repeat(indent)
    appendLine("$prefix$key: |-")
    value.lines().forEach { line ->
        appendLine("$prefix  $line".trimEnd())
    }
}

fun extractType(prop: Property): Pair<String, String?> = when {
    prop.type == "array" -> {
        val items = prop.items ?: error("array without items")
        val (itemType, _) = extractType(items)
        itemType to "array"
    }
    prop.type == "object" && prop.additionalProperties != null -> {
        val (valueType, _) = extractType(prop.additionalProperties)
        valueType to "map"
    }
    prop.type != null -> prop.type to null
    prop.ref != null -> extractRefType(prop.ref) to null
    prop.allOf != null -> {
        val ref = prop.allOf.singleOrNull()?.ref
            ?: error("Expected single allOf with ref: $prop")
        extractRefType(ref) to null
    }
    else -> error("unknown property type: $prop")
}

fun extractRefType(ref: String): String =
    ref.removePrefix("#/components/schemas/").substringAfterLast(".")
