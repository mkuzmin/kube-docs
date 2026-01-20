package app

import com.charleskorn.kaml.MultiLineStringStyle
import com.charleskorn.kaml.SingleLineStringStyle
import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.Serializable

val yaml = Yaml(
    configuration = YamlConfiguration(
        singleLineStringStyle = SingleLineStringStyle.Plain,
        multiLineStringStyle = MultiLineStringStyle.Literal,
        encodeDefaults = false,
        breakScalarsAt = 300,
    )
)

@Serializable
data class TypeYaml(
    val description: Description,
    val kind: Boolean? = null,
)

@Serializable
data class FieldYaml(
    val description: Description? = null,
    val type: String? = null,
    val collection: String? = null,
    val required: Boolean? = null,
    val enum: Boolean? = null,
)

@Serializable
data class Description(
    val original: String,
    val formatted: String,
)

data class Type(
    val yaml: TypeYaml,
    val fields: Map<String, FieldYaml>,
)
