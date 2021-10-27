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

package kasem.sm.delightplayground.features.rocket_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import kasem.sm.delightplayground.R
import kasem.sm.delightplayground.core.components.Image
import kasem.sm.delightplayground.datasource.Rocket

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RocketItem(
    rocket: Rocket,
    imageLoader: ImageLoader,
    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize()
            .padding(12.dp)
            .clip(
                CutCornerShape(
                    topEnd = 6.dp, bottomStart = 18.dp,
                    topStart = 6.dp, bottomEnd = 6.dp
                ),
            )
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = {
                    onItemClicked(rocket.id.toInt())
                }
            ),
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
        shape = CutCornerShape(
            topEnd = 6.dp, bottomStart = 18.dp,
            topStart = 6.dp, bottomEnd = 6.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {

            /**
             * Get the first url from list
             * */

            val imageUrl = rocket.image[1]

            imageLoader.Image(
                modifier = Modifier
                    .height(200.dp),
                data = imageUrl
            )
            RocketListItemDescription(
                text = rocket.rocketTitle,
                icon = R.drawable.ic_rocket,
                isActive = rocket.active.toInt() == 1
            )
            RocketListItemDescription(
                text = rocket.country,
                icon = R.drawable.ic_pin
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
