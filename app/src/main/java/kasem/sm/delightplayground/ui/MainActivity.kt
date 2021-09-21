package kasem.sm.delightplayground.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kasem.sm.delightplayground.features.rocket_detail.RocketDetailScreen
import kasem.sm.delightplayground.features.rocket_list.RocketListEvent
import kasem.sm.delightplayground.features.rocket_list.RocketListScreen
import kasem.sm.delightplayground.features.rocket_list.RocketListViewModel
import kasem.sm.delightplayground.ui.navigation.Screen
import kasem.sm.delightplayground.ui.theme.DelightPlaygroundTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DelightPlaygroundTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Screen.ListScreen.route,
                ) {
                    // List Screen
                    composable(
                        route = Screen.ListScreen.route,
                    ) {
                        val viewModel: RocketListViewModel = hiltViewModel()

                        RocketListScreen(
                            state = viewModel.state.value,
                            imageLoader = imageLoader,
                            provokeGetRocket = { viewModel.provoke(RocketListEvent.GetRockets) },
                            onItemClick = { rocketId ->
                                navController.navigate(
                                    route = "${Screen.DetailScreen.route}/$rocketId",
                                )
                            }
                        )
                    }
                    // Detail Screen
                    composable(
                        route = "${Screen.DetailScreen.route}/{rocketId}",
                        arguments = Screen.DetailScreen.arguments
                    ) {

                        RocketDetailScreen(rocketId = it.arguments?.getInt("rocketId"))
                    }
                }
            }
        }
    }
}
