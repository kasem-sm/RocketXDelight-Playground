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

package kasem.sm.delightplayground.features.rocket_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Web
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import kasem.sm.delightplayground.core.components.DelightTopBar
import kasem.sm.delightplayground.core.components.Image
import kasem.sm.delightplayground.core.components.MenuItem
import kasem.sm.delightplayground.datasource.Rocket

@Composable
fun RocketDetail(
    isLoading: Boolean,
    rocket: Rocket,
    imageLoader: ImageLoader,
    onMenuItemClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Column {
            DelightTopBar(
                modifier = Modifier
                    .padding(4.dp),
                titleText = rocket.rocketTitle,
                descriptionText = "Initial Flight: ${rocket.firstFlight}",
                menuItems = listOf(
                    MenuItem(iconVector = Icons.Outlined.Web) {
                        onMenuItemClicked()
                    }
                )
            )

            Spacer(
                modifier = Modifier
                    .height(5.dp)
            )

            val listOfImages = rocket.image.toList()

            listOfImages.forEachIndexed { p, data ->
                Card(
                    modifier = Modifier
                        .padding(15.dp)
                        .height(250.dp),
                    shape = CutCornerShape(
                        topEnd = 4.dp, bottomStart = if (p % 2 == 0) 18.dp else 4.dp,
                        topStart = 4.dp, bottomEnd = if (p % 2 == 1) 18.dp else 4.dp
                    ),
                    elevation = 0.dp,
                    border = BorderStroke(
                        width = 1.dp,
                        brush = Brush.verticalGradient(
                            colors = if (isSystemInDarkTheme()) {
                                listOf(Color.Transparent, MaterialTheme.colorScheme.inversePrimary)
                            } else {
                                listOf(Color.Transparent, Color.Black.copy(alpha = 0.2f))
                            }
                        )
                    ),
                ) {
                    imageLoader.Image(
                        modifier = Modifier
                            .fillMaxSize(),
                        data = data
                    )
                }
            }
        }
    }
}
