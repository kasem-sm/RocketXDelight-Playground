package kasem.sm.delightplayground.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Diameter(
    val feet: Double,
    val meters: Double
)
