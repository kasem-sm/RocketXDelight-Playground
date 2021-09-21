package kasem.sm.delightplayground.datasource.network.model

import kotlinx.serialization.Serializable

@Serializable
data class Mass(
    val kg: Int,
    val lb: Int
)
