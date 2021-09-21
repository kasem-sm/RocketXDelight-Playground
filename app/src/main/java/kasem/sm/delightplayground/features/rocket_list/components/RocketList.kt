package kasem.sm.delightplayground.features.rocket_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kasem.sm.delightplayground.features.rocket_list.RocketState

@Composable
fun RocketList(
    state: RocketState,
    imageLoader: ImageLoader,
    provokeGetRocket: () -> Unit,
    onItemClicked: (Int) -> Unit
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
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(4.dp)
            ) {
                items(state.rockets) {
                    RocketListItem(
                        rocket = it,
                        imageLoader = imageLoader,
                        onItemClicked = onItemClicked
                    )
                }
            }
        }
    }
}
