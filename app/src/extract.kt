package app

import java.io.File

fun main() {
    val apiGroups = loadApiGroups("../api-groups.csv")
    val specDir = File("../data/openapi")
    val outDir = File("../data/types")

    apiGroups.filter { it.preferredVersion }.forEach { group ->
        val schemas = loadSchemas(specDir, group)

        schemas.forEach { (typeName, schema) ->
            val typeDir = File(outDir, "${group.group}/$typeName")
                .also { it.mkdirs() }

            writeType(File(typeDir, "_$typeName.yaml"), schema)

            schema.properties.forEach { (fieldName, prop) ->
                if (fieldName !in setOf("apiVersion", "kind", "metadata")) {
                    writeField(File(typeDir, "$fieldName.yaml"), prop)
                }
            }
        }
    }
}

fun writeType(file: File, schema: Schema) {
    val doc = TypeDoc(
        description = Description(
            original = schema.description,
            formatted = schema.description,
        )
    )
    val text = yaml.encodeToString(TypeDoc.serializer(), doc)
    file.writeText(text)
}

fun writeField(file: File, prop: Property) {
    if (prop.description == null) {
        file.writeText("")
        return
    }

    val doc = FieldDoc(
        description = Description(
            original = prop.description,
            formatted = prop.description,
        )
    )
    val text = yaml.encodeToString(FieldDoc.serializer(), doc)
    file.writeText(text)
}
