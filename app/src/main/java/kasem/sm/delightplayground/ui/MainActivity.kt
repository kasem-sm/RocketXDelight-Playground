/*
 Copyright (c) 2021 Kasem S.M.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package kasem.sm.delightplayground.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kasem.sm.delightplayground.features.rocket_detail.RocketDetailScreen
import kasem.sm.delightplayground.features.rocket_detail.RocketDetailViewModel
import kasem.sm.delightplayground.features.rocket_list.RocketListScreen
import kasem.sm.delightplayground.features.rocket_list.RocketListViewModel
import kasem.sm.delightplayground.ui.navigation.Screen
import kasem.sm.delightplayground.ui.theme.DelightPlaygroundTheme

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DelightPlaygroundTheme() {
                val navController = rememberNavController()

                BoxWithConstraints {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.ListScreen.route,
                    ) {
                        // List Screen
                        attachListScreen(
                            navController = navController
                        )
                        // Detail Screen
                        attachDetailScreen()
                    }
                }
            }
        }
    }

    @ExperimentalAnimationApi
    private fun NavGraphBuilder.attachListScreen(
        navController: NavController
    ) {
        composable(
            route = Screen.ListScreen.route,
        ) {
            val viewModel: RocketListViewModel = hiltViewModel()
            val state = viewModel.state.value

            val context = LocalContext.current

            LaunchedEffect(key1 = true) {
                state.errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            RocketListScreen(
                state = state,
                imageLoader = imageLoader,
                provokeGetRocket = { viewModel.getRockets() },
                onItemClick = { rocketId ->
                    navController.navigate(
                        route = "${Screen.DetailScreen.route}/$rocketId",
                    )
                },
                onMenuItemClicked = {
                    openUrl(DELIGHT_REPO)
                }
            )
        }
    }

    private fun NavGraphBuilder.attachDetailScreen() {
        composable(
            route = "${Screen.DetailScreen.route}/{rocketId}",
            arguments = Screen.DetailScreen.arguments,
        ) {
            val viewModel: RocketDetailViewModel = hiltViewModel()
            val state = viewModel.state.value

            val context = LocalContext.current

            LaunchedEffect(key1 = true) {
                state.errorMessage?.let {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

            RocketDetailScreen(
                state = state,
                imageLoader = imageLoader
            ) {
                state.rocket?.wikipedia?.let { url ->
                    openUrl(url)
                }
            }
        }
    }

    companion object {
        private const val DELIGHT_REPO = "https://github.com/kasem-sm/RocketXDelight-Playground"
    }
}

fun Activity.openUrl(url: String) =
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
