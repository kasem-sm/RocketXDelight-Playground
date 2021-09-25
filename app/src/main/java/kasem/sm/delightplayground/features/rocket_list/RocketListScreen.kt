package kasem.sm.delightplayground.features.rocket_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kasem.sm.delightplayground.R
import kasem.sm.delightplayground.core.components.DelightTopBar
import kasem.sm.delightplayground.core.components.MenuItem
import kasem.sm.delightplayground.core.components.widgets.StaggeredVerticalGrid
import kasem.sm.delightplayground.features.rocket_list.components.RocketItem
import kasem.sm.delightplayground.ui.theme.delightBackground

@Composable
fun RocketListScreen(
    state: RocketState,
    imageLoader: ImageLoader,
    provokeGetRocket: () -> Unit,
    onItemClick: (Int) -> Unit,
    onMenuItemClicked: () -> Unit
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)

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
                .background(delightBackground())
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
