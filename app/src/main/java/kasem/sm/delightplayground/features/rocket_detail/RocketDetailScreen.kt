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
