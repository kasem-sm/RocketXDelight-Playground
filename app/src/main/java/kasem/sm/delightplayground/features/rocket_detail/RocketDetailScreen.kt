package kasem.sm.delightplayground.features.rocket_detail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import kasem.sm.delightplayground.features.rocket_detail.components.RocketDescription
import kasem.sm.delightplayground.features.rocket_detail.components.RocketDetail
import kasem.sm.delightplayground.ui.theme.delightBottomSheetColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun RocketDetailScreen(
    state: RocketDetailState,
    imageLoader: ImageLoader,
    onMenuItemClicked: () -> Unit
) {

    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BackHandler(
        enabled = bottomSheetScaffoldState.bottomSheetState.isExpanded ||
            bottomSheetScaffoldState.bottomSheetState.isAnimationRunning
    ) {
        scope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    }

    state.rocket?.let { rocket ->
        BottomSheetScaffold(
            modifier = Modifier
                .fillMaxSize(),
            scaffoldState = bottomSheetScaffoldState,
            sheetShape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            sheetBackgroundColor = delightBottomSheetColor(),
            sheetContent = {
                RocketDescription(rocket = rocket)
            }
        ) {
            /**
             * Default bottom sheet peek height is 56.dp
             * so the content should have a padding of the same or more from bottom
             */
            LazyColumn(
                modifier = Modifier
                    .padding(bottom = 56.dp)
            ) {
                item {
                    RocketDetail(
                        isLoading = state.isLoading,
                        rocket = rocket,
                        imageLoader = imageLoader,
                        onMenuItemClicked = onMenuItemClicked
                    )
                }
            }
        }
    }
}
