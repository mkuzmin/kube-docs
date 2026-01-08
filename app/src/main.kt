package app

import kotlinx.serialization.json.Json
import java.io.File

const val SPEC_PATH = "../kubernetes/api/openapi-spec/v3/apis__batch__v1_openapi.json"
const val PACKAGE_PREFIX = "io.k8s.api.batch.v1."
const val OUTPUT_DIR = "../docs/pages/generated"

fun main() {
    val outDir = File(OUTPUT_DIR)
    outDir.mkdirs()

    val schemas = loadSchemas(SPEC_PATH, PACKAGE_PREFIX)
    generatePages(schemas, outDir, PACKAGE_PREFIX)
    generateIndex(schemas, outDir, "batch___v1.md")
}

fun loadSchemas(specPath: String, packagePrefix: String): Map<String, Schema> {
    val json = Json { ignoreUnknownKeys = true }
    val spec = json.decodeFromString<OpenApiSpec>(File(specPath).readText())
    return spec.components.schemas
        .filterKeys { it.startsWith(packagePrefix) }
        .filterKeys { !it.endsWith("List") }
        .mapKeys { (key, _) -> key.removePrefix(packagePrefix) }
}

fun generatePages(schemas: Map<String, Schema>, outDir: File, packagePrefix: String) {
    val typesDir = File(outDir, "types")
    typesDir.mkdirs()

    schemas.forEach { (name, schema) ->
        val dir = if (schema.kind != null) outDir else typesDir
        val file = File(dir, "$name.md")
        file.writeText(buildString {
            appendLine("- Properties")
            appendLine("  heading:: true")
            schema.properties.forEach { (name, prop) ->
                val typeStr = formatPropertyType(prop, packagePrefix)
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
    prop.allOf != null -> {
        val ref = prop.allOf.firstOrNull()?.ref
            ?: error("missing ref in allOf")
        val fullName = ref.removePrefix("#/components/schemas/")
        val typeName = fullName.substringAfterLast(".")
        if (fullName.startsWith(packagePrefix))
            "[[$typeName]]"
        else
            typeName
    }
    else -> error("unknown property type: $prop")
}

fun generateIndex(schemas: Map<String, Schema>, outDir: File, filename: String) {
    val file = File(outDir, filename)
    file.writeText(buildString {
        schemas
            .filter { (_, schema) -> schema.kind != null }
            .forEach { (name, _) ->
                appendLine("- [[$name]]")
            }
    })
}
