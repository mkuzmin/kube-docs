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
    generatePages(schemas, outDir)
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

fun generatePages(schemas: Map<String, Schema>, outDir: File) {
    schemas.forEach { (name, schema) ->
        val file = File(outDir, "$name.md")
        file.writeText(buildString {
            appendLine("- Properties")
            appendLine("  heading:: true")
            schema.properties.forEach { (name, _) ->
                appendLine("  - `$name`")
            }
        })
    }
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
