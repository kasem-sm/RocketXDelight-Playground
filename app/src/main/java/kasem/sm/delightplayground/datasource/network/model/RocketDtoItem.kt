package kasem.sm.delightplayground.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RocketDtoItem(
    @SerialName("id")
    val id: Int,
    @SerialName("active")
    val active: Boolean,
    @SerialName("company")
    val company: String,
    @SerialName("cost_per_launch")
    val costPerLaunch: Int,
    @SerialName("country")
    val country: String,
    @SerialName("description")
    val description: String,
    @SerialName("diameter")
    val diameter: Diameter,
    @SerialName("first_flight")
    val firstFlight: String,
    @SerialName("flickr_images")
    val imagesList: List<String>,
    @SerialName("height")
    val height: Height,
    @SerialName("mass")
    val mass: Mass,
    @SerialName("rocket_id")
    val rocketId: String,
    @SerialName("rocket_name")
    val rocketName: String,
    @SerialName("rocket_type")
    val rocketType: String,
    @SerialName("stages")
    val stages: Int,
    @SerialName("wikipedia")
    val wikipedia: String
)
