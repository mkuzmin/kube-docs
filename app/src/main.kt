package app

import java.io.File

fun main() {
    val apiGroups = loadApiGroups("../api-groups.csv")
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
        val types = loadTypes(typesDir, groupVersion)

        generateGroupVersionIndex(types, groupVersion, groupDir)
        generatePages(types, groupVersion, groupDir)
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

fun loadTypes(typesDir: File, group: ApiGroup): Map<String, Type> {
    val groupDir = File(typesDir, group.group)
    return (groupDir.listFiles()
        ?: error("Cannot list directory: $groupDir"))
        .filter { it.isDirectory }
        .associate { typeDir ->
            val typeName = typeDir.name
            val typeYaml = yaml.decodeFromString(TypeYaml.serializer(), File(typeDir, "_$typeName.yaml").readText())
            val fields = (typeDir.listFiles()
                ?: error("Cannot list directory: $typeDir"))
                .filter { it.name.endsWith(".yaml") && !it.name.startsWith("_") }
                .associate { file ->
                    val fieldName = file.nameWithoutExtension
                    val fieldYaml = yaml.decodeFromString(FieldYaml.serializer(), file.readText())
                    fieldName to fieldYaml
                }
            typeName to Type(typeYaml, fields)
        }
}

fun generatePages(types: Map<String, Type>, group: ApiGroup, outDir: File) {
    types.forEach { (typeName, type) ->
        val filename = "${group.group}___${group.apiVersion}___$typeName.md"
        val file = File(outDir, filename)

        file.writeText(buildString {
            appendLine("alias:: $typeName")
            appendLine()

            appendBlock(level = 0, type.yaml.description.formatted)
            appendLine()

            appendLine("- Properties")
            appendLine("  heading:: true")
            appendLine()

            val standardFields = if (type.yaml.kind == true) {
                listOf("apiVersion" to "string", "kind" to "string", "metadata" to "ObjectMeta")
            } else emptyList()

            val allFieldNames = (standardFields.map { it.first } + type.fields.keys).distinct().sorted()

            allFieldNames.forEach { fieldName ->
                val standardField = standardFields.find { it.first == fieldName }
                val field = type.fields[fieldName]

                val typeStr = when {
                    standardField != null -> standardField.second
                    field != null -> formatFieldType(field, types)
                    else -> error("Field '$fieldName' not found in type or standard fields")
                }
                val requiredStr = if (field?.required == true) ", **required**" else ""
                appendLine("  - `$fieldName` ($typeStr)$requiredStr")

                if (fieldName !in setOf("apiVersion", "kind", "metadata")) {
                    val description = field?.description?.formatted
                    if (description != null) {
                        appendBlock(level = 2, description)
                    }
                }
                appendLine()
            }
        })
    }
}

fun StringBuilder.appendBlock(level: Int, text: String) {
    val indent = "  ".repeat(level)
    val lines = text.split("\n")
    appendLine("$indent- ${lines.first()}")
    lines.drop(1).forEach { appendLine("$indent  $it") }
}

fun formatFieldType(field: FieldYaml, types: Map<String, Type>): String {
    if (field.collection == "map") return "object"
    val baseType = field.type ?: "object"
    val formattedType = if (baseType[0].isUpperCase() && baseType in types) "[[$baseType]]" else baseType
    return when (field.collection) {
        "array" -> "[]$formattedType"
        else -> formattedType
    }
}

fun generateGroupVersionIndex(types: Map<String, Type>, group: ApiGroup, outDir: File) {
    val filename = "${group.group}___${group.apiVersion}.md"
    val file = File(outDir, filename)
    val lines = types.filterValues { it.yaml.kind == true }.keys.sorted().map { "- [[$it]]" }
    file.writeText(lines.joinToString("\n", postfix = "\n"))
}

fun generateGroupIndex(name: String, versions: List<ApiGroup>, groupDir: File) {
    val file = File(groupDir, "$name.md")
    val lines = versions.map { ver ->
        if (ver.preferredVersion) "- [${ver.apiVersion}]([[${ver.groupVersion}]])"
        else "- ${ver.apiVersion}"
    }
    file.writeText(lines.joinToString("\n", postfix = "\n"))
}

fun generateApiIndex(groups: Set<String>, dir: File) {
    val file = File(dir, "API.md")
    val lines = groups.map { "- [[$it]]" }
    file.writeText(lines.joinToString("\n", postfix = "\n"))
}
