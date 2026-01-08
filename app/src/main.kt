package app

import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val apiGroups = loadApiGroups("../api-groups.csv")
    apiGroups.filter { it.preferredVersion }.forEach { group ->
        val dir = File("../docs/pages/generated/${group.group}").also { it.mkdirs() }
        val schemas = loadSchemas(group)
        generatePages(schemas, dir, group)
        generateIndex(schemas, dir, group)
    }
}

fun loadApiGroups(filename: String): List<ApiGroup> = File(filename).readLines().drop(1) // skip header
    .filter { it.isNotBlank() }.map { line ->
        val columns = line.split(",")
        require(columns.size == 5) { "in '$filename' expected 5 columns, got ${columns.size}: $line" }
        ApiGroup(
            group = columns[0],
            apiVersion = columns[1],
            preferredVersion = columns[2] == "true",
            pkg = columns[3],
            file = columns[4]
        )
    }

data class ApiGroup(
    val group: String, val apiVersion: String, val preferredVersion: Boolean, val pkg: String, val file: String
) {
    val groupVersion: String get() = "$group/$apiVersion"
    val packagePrefix: String get() = "$pkg.$apiVersion."
}

fun loadSchemas(group: ApiGroup): Map<String, Schema> {
    val json = Json { ignoreUnknownKeys = true }
    val specPath = "../kubernetes/api/openapi-spec/v3/${group.file}"
    val spec = json.decodeFromString<OpenApiSpec>(File(specPath).readText())
    return spec.components.schemas.filterKeys { it.startsWith(group.packagePrefix) }.filterKeys { !it.endsWith("List") }
        .mapKeys { (key, _) -> key.removePrefix(group.packagePrefix) }
}

fun generatePages(schemas: Map<String, Schema>, outDir: File, group: ApiGroup) {
    val typesDir = File(outDir, "types").also { it.mkdirs() }

    schemas.forEach { (name, schema) ->
        val dir = if (schema.kind != null) outDir else typesDir
        val filename = "${group.group}___${group.apiVersion}___$name.md"
        val file = File(dir, filename)
        file.writeText(buildString {
            appendLine("alias:: $name")
            appendLine()
            appendLine("- Properties")
            appendLine("  heading:: true")
            schema.properties.forEach { (name, prop) ->
                val typeStr = formatPropertyType(prop, group.packagePrefix)
                appendLine("  - `$name` ($typeStr)")
            }
        })
    }
}

fun formatPropertyType(prop: Property, packagePrefix: String): String = when {
    prop.type == "array" -> {
        val items = prop.items ?: error("array without items")
        "[]${formatPropertyType(items, packagePrefix)}"
    }

    prop.type != null -> prop.type
    prop.ref != null -> formatRef(prop.ref, packagePrefix)
    prop.allOf != null -> formatRef(prop.allOf.single().ref, packagePrefix)

    else -> error("unknown property type: $prop")
}

fun formatRef(ref: String, packagePrefix: String): String {
    val fullName = ref.removePrefix("#/components/schemas/")
    val typeName = fullName.substringAfterLast(".")
    return if (fullName.startsWith(packagePrefix)) "[[$typeName]]"
    else typeName
}

fun generateIndex(schemas: Map<String, Schema>, outDir: File, group: ApiGroup) {
    val filename = "${group.group}___${group.apiVersion}.md"
    val file = File(outDir, filename)
    file.writeText(buildString {
        schemas.filter { (_, schema) -> schema.kind != null }.forEach { (name, _) ->
            appendLine("- [[$name]]")
        }
    })
}
