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

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kasem.sm.delightplayground.core.components.DelightIcon
import kasem.sm.delightplayground.core.components.DelightText
import kasem.sm.delightplayground.core.components.utils.regularFont
import kasem.sm.delightplayground.datasource.Rocket

@Composable
fun RocketDescription(
    rocket: Rocket
) {
    Column {
        Row {
            DelightIcon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp, top = 16.dp, bottom = 10.dp),
                icon = Icons.Outlined.Description,
                size = 18.dp,
                addSpace = true
            )

            DelightText(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 16.dp, bottom = 10.dp),
                text = "Swipe up to read about ${rocket.rocketTitle}.",
                font = regularFont(),
                fontSize = 14.sp
            )
        }

        DelightText(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    horizontal = 18.dp,
                    vertical = 10.dp
                ),
            text = rocket.description,
            font = regularFont()
        )
    }
}
