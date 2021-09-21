package kasem.sm.delightplayground.features.rocket_list

import kasem.sm.delightplayground.datasource.Rocket

data class RocketState(
    val isLoading: Boolean = false,
    val rockets: List<Rocket> = listOf(),
    val errorMessage: String = "Error Message"
)
