package kasem.sm.delightplayground.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(
    val route: String,
    val arguments: List<NamedNavArgument>
) {

    object ListScreen : Screen(
        route = "listScreen",
        arguments = emptyList()
    )

    object DetailScreen : Screen(
        route = "detailScreen",
        arguments = listOf(
            navArgument("rocketId") {
                type = NavType.IntType
            }
        )
    )
}
