package app

import java.io.File

fun main() {
    val apiGroups = loadApiGroups("../api-groups.csv")
    val specDir = File("../kubernetes/api/openapi-spec/v3")
    val outDir = File("../specs")

    apiGroups.filter { it.preferredVersion }.forEach { group ->
        val schemas = loadSchemas(specDir, group)

        schemas.forEach { (name, schema) ->
            val typeDir = File(outDir, "${group.group}/${group.apiVersion}/$name")
                .also { it.mkdirs() }

            File(typeDir, "_$name.md").writeText(schema.description)

            schema.properties.forEach { (fieldName, prop) ->
                if (fieldName !in setOf("apiVersion", "kind", "metadata")) {
                    prop.description?.let { description ->
                        File(typeDir, "$fieldName.md").writeText(description)
                    }
                }
            }
        }
    }
}
