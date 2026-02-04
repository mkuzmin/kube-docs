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
                val requiredOpenapi = fieldName in schema.required
                val requiredKind = schema.kind != null && fieldName in setOf("apiVersion", "kind", "metadata")
                val skipDescription = fieldName in setOf("apiVersion", "kind", "metadata")
                writeField(File(typeDir, "$fieldName.yaml"), prop, requiredOpenapi, requiredKind, skipDescription)
            }
        }
    }
}

fun writeType(file: File, schema: Schema) {
    file.parentFile.mkdirs()
    if (!file.exists()) file.createNewFile()

    // description.original - always update
    yq(file, ".description.original = strenv(DESC) | .description.original style=\"literal\"", "DESC" to schema.description)

    // description.formatted - set only if missing (preserves edits)
    yq(file, ".description.formatted = (.description.formatted // strenv(DESC)) | .description.formatted style=\"literal\"", "DESC" to schema.description)

    // kind - set if true, delete if false/null
    if (schema.kind != null) {
        yq(file, ".kind = true")
    } else {
        yq(file, "del(.kind)")
    }
}

fun writeField(file: File, prop: Property, requiredOpenapi: Boolean, requiredKind: Boolean, skipDescription: Boolean) {
    val (type, collection) = extractType(prop)

    file.parentFile.mkdirs()
    if (!file.exists()) file.createNewFile()

    // description - skip for apiVersion/kind/metadata
    if (!skipDescription && prop.description != null) {
        yq(file, ".description.original = strenv(DESC) | .description.original style=\"literal\"", "DESC" to prop.description)
        yq(file, ".description.formatted = (.description.formatted // strenv(DESC)) | .description.formatted style=\"literal\"", "DESC" to prop.description)
    }

    // type - always present
    yq(file, ".type = \"$type\"")

    // collection - set or delete
    if (collection != null) {
        yq(file, ".collection = \"$collection\"")
    } else {
        yq(file, "del(.collection)")
    }

    // required block - set entire block or delete
    when {
        requiredOpenapi && requiredKind -> yq(file, ".required = {\"openapi\": true, \"kind\": true}")
        requiredOpenapi -> yq(file, ".required = {\"openapi\": true}")
        requiredKind -> yq(file, ".required = {\"kind\": true}")
        else -> yq(file, "del(.required)")
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

fun yq(file: File, expression: String, vararg env: Pair<String, String>) {
    val envArray = env.flatMap { (k, v) -> listOf("$k=$v") }.toTypedArray()
    val process = ProcessBuilder("env", *envArray, "yq", "-i", expression, file.absolutePath)
        .redirectErrorStream(true)
        .start()
    val output = process.inputStream.bufferedReader().readText()
    val exitCode = process.waitFor()
    if (exitCode != 0) error("yq failed (exit $exitCode): $output")
}
