package app

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
data class OpenApiSpec(
    val components: Components,
)

@Serializable
data class Components(
    val schemas: Map<String, Schema>,
)

@Serializable
data class Schema(
    val properties: Map<String, Property> = emptyMap(),

    @SerialName("x-kubernetes-group-version-kind")
    val kind: JsonArray? = null,
)

@Serializable
data class Property(
    val type: String? = null,
    val allOf: List<RefObject>? = null,
    val items: Property? = null,
    @SerialName($$"$ref")
    val ref: String? = null,
)

@Serializable
data class RefObject(
    @SerialName($$"$ref")
    val ref: String,
)
