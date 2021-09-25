package kasem.sm.delightplayground.features.rocket_detail

import kasem.sm.delightplayground.datasource.Rocket

data class RocketDetailState(
    val isLoading: Boolean = false,
    val rocket: Rocket? = null,
    val errorMessage: String? = null
)
