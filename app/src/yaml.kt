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
    )
)

@Serializable
data class TypeDoc(
    val description: Description,
)

@Serializable
data class FieldDoc(
    val description: Description? = null,
)

@Serializable
data class Description(
    val original: String,
    val formatted: String,
)
