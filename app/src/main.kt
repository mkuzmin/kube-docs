package app

import kotlinx.serialization.json.Json
import java.io.File

private const val SPEC_PATH = "../kubernetes/api/openapi-spec/v3/apis__batch__v1_openapi.json"
private const val OUTPUT_DIR = "../docs/pages/generated"

fun main() {
    val json = Json { ignoreUnknownKeys = true }
    val text = File(SPEC_PATH).readText()
    val spec = json.decodeFromString<OpenApiSpec>(text)
    val cronJob = spec.components.schemas["io.k8s.api.batch.v1.CronJob"]
        ?: error("CronJob schema not found")

    val outDir = File(OUTPUT_DIR)
    outDir.mkdirs()
    val outFile = File(outDir, "CronJob.md")
    outFile.writeText(buildString {
        appendLine("- Properties")
        appendLine("  heading:: true")

        cronJob.properties.keys.forEach {
            appendLine("  - `$it`")
        }
    })
}
