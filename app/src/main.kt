package app

import kotlinx.serialization.json.Json
import java.io.File

fun main() {
    val apiGroups = loadApiGroups("../api-groups.csv")
    val specDir = File("../data/openapi")
    val typesDir = File("../data/types")
    val baseDir = File("../data/logseq/pages/generated")

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
        generatePages(schemas, groupVersion, typesDir, groupDir)
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

fun generatePages(schemas: Map<String, Schema>, group: ApiGroup, typesDir: File, outDir: File) {
    val helperTypesDir = File(outDir, "types").also { it.mkdirs() }

    schemas.forEach { (typeName, schema) ->
        val dir = if (schema.kind != null) outDir else helperTypesDir
        val filename = "${group.group}___${group.apiVersion}___$typeName.md"
        val file = File(dir, filename)

        val typeMetaDir = File(typesDir, "${group.group}/$typeName")
        val typeDescription = readFormatted(File(typeMetaDir, "_$typeName.yaml"))

        file.writeText(buildString {
            appendLine("alias:: $typeName")
            appendLine()

            if (typeDescription != null) {
                appendBlock(level = 0, typeDescription)
                appendLine()
            }

            appendLine("- Properties")
            appendLine("  heading:: true")
            appendLine()

            schema.properties.forEach { (fieldName, prop) ->
                val typeStr = formatPropertyType(prop, group.packagePrefix)
                val requiredStr = if (fieldName in schema.required) ", **required**" else ""
                appendLine("  - `$fieldName` ($typeStr)$requiredStr")
                if (fieldName !in setOf("apiVersion", "kind", "metadata")) {
                    val fieldDescription = readFormatted(File(typeMetaDir, "$fieldName.yaml"))
                    if (fieldDescription != null) {
                        appendBlock(level = 2, fieldDescription)
                    }
                }
                appendLine()
            }
        })
    }
}

fun readFormatted(file: File): String? {
    if (!file.exists()) return null
    val text = file.readText()
    if (text.isBlank()) return null
    val doc = yaml.decodeFromString(FieldDoc.serializer(), text)
    return doc.description?.formatted
}

fun StringBuilder.appendBlock(level: Int, text: String) {
    val indent = "  ".repeat(level)
    val lines = text.split("\n")
    appendLine("$indent- ${lines.first()}")
    lines.drop(1).forEach { appendLine("$indent  $it") }
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
