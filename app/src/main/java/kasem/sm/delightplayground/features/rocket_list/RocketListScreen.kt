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

package kasem.sm.delightplayground.features.rocket_list

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kasem.sm.delightplayground.R
import kasem.sm.delightplayground.core.components.DelightTopBar
import kasem.sm.delightplayground.core.components.MenuItem
import kasem.sm.delightplayground.core.components.widgets.StaggeredVerticalGrid
import kasem.sm.delightplayground.features.rocket_list.components.RocketItem

@Composable
fun RocketListScreen(
    state: RocketState,
    imageLoader: ImageLoader,
    provokeGetRocket: () -> Unit,
    onItemClick: (Int) -> Unit,
    onMenuItemClicked: () -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = isSystemInDarkTheme()

    val currentPrimaryColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.2f)
    val currentBackgroundColor = MaterialTheme.colorScheme.background

    LaunchedEffect(key1 = true) {
        systemUiController.run {
            setSystemBarsColor(
                color = currentPrimaryColor,
                darkIcons = !useDarkIcons
            )
            setNavigationBarColor(
                currentBackgroundColor
            )
        }
    }

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            provokeGetRocket()
        },
        indicator = { srs, trigger ->
            SwipeRefreshIndicator(
                state = srs,
                refreshTriggerDistance = trigger,
                scale = true,
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LazyColumn(
                contentPadding = PaddingValues(4.dp)
            ) {
                item {
                    DelightTopBar(
                        titleText = "Delight",
                        descriptionText = "Explore  ∞  Infinity",
                        menuItems = listOf(
                            MenuItem(icon = R.drawable.ic_github) {
                                onMenuItemClicked()
                            }
                        )
                    )
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
                item {
                    /**
                     * https://github.com/Gurupreet/ComposeCookBook/blob/master/components/verticalgrid/src/main/java/com/guru/composecookbook/verticalgrid/StaggeredVerticalGrid.kt
                     * */
                    StaggeredVerticalGrid {
                        state.rockets.forEach {
                            RocketItem(
                                rocket = it,
                                imageLoader = imageLoader,
                                onItemClicked = onItemClick
                            )
                        }
                    }
                }
            }
        }
    }
}
