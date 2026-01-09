package app

import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val apiGroups = loadApiGroups("../api-groups.csv")
    val specDir = File("../kubernetes/api/openapi-spec/v3")
    val baseDir = File("../docs/pages/generated")

    val groups = apiGroups.groupBy { it.group }

    generateApiIndex(groups.keys, baseDir)

    groups.forEach { (name, versions) ->
            val groupDir = File(baseDir, name)
                .also { it.mkdirs() }

            generateGroupIndex(name, versions, groupDir)

            val preferred = versions.filter { it.preferredVersion }
            if (preferred.isEmpty()) return@forEach
            if (preferred.size > 1) error("Multiple preferred versions for group '$name'")

            val groupVersion = preferred.single()
            val schemas = loadSchemas(specDir, groupVersion)

            generateGroupVersionIndex(schemas, groupVersion, groupDir)
            generatePages(schemas, groupVersion, groupDir)
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

fun loadSchemas(specDir: File, group: ApiGroup): Map<String, Schema> {
    val json = Json { ignoreUnknownKeys = true }
    val spec = json.decodeFromString<OpenApiSpec>(File(specDir, group.file).readText())
    return spec.components.schemas.filterKeys { it.startsWith(group.packagePrefix) }.filterKeys { !it.endsWith("List") }
        .mapKeys { (key, _) -> key.removePrefix(group.packagePrefix) }
}

fun generatePages(schemas: Map<String, Schema>, group: ApiGroup, outDir: File) {
    val typesDir = File(outDir, "types").also { it.mkdirs() }

    schemas.forEach { (name, schema) ->
        val dir = if (schema.kind != null) outDir else typesDir
        val filename = "${group.group}___${group.apiVersion}___$name.md"
        val file = File(dir, filename)
        file.writeText(buildString {
            appendLine("alias:: $name")
            appendLine()
            appendLine("- ${schema.description}")
            appendLine()
            appendLine("- Properties")
            appendLine("  heading:: true")
            appendLine()
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

fun generateGroupVersionIndex(schemas: Map<String, Schema>, group: ApiGroup, outDir: File) {
    val filename = "${group.group}___${group.apiVersion}.md"
    val file = File(outDir, filename)
    file.writeText(buildString {
        schemas.filter { (_, schema) -> schema.kind != null }.forEach { (name, _) ->
            appendLine("- [[$name]]")
        }
    })
}

fun generateGroupIndex(name: String, versions: List<ApiGroup>, groupDir: File) {
    val file = File(groupDir, "$name.md")
    file.writeText(buildString {
        versions.forEach { ver ->
            if (ver.preferredVersion) {
                appendLine("- [${ver.apiVersion}]([[${ver.groupVersion}]])")
            } else {
                appendLine("- ${ver.apiVersion}")
            }
        }
    })
}

fun generateApiIndex(groups: Set<String>, dir: File) {
    val file = File(dir, "API.md")
    file.writeText(buildString {
        groups.forEach { name ->
            appendLine("- [[$name]]")
        }
    })
}
