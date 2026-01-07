package app

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class OpenApiSpec(
    val components: Components
)

@Serializable
data class Components(
    val schemas: Map<String, Schema>
)

@Serializable
data class Schema(
    val properties: Map<String, JsonObject> = emptyMap()
)