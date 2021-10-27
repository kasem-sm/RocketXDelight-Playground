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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kasem.sm.delightplayground.core.components.DelightIcon
import kasem.sm.delightplayground.core.components.DelightText
import kasem.sm.delightplayground.core.components.utils.regularFont

@Composable
fun RocketListItemDescription(
    modifier: Modifier = Modifier,
    text: String,
    icon: Int,
    isActive: Boolean? = null
) {
    Row(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        DelightIcon(
            modifier = Modifier
                .padding(8.dp)
                .wrapContentSize(),
            size = 16.dp,
            icon = icon,
            addSpace = true
        )

        DelightText(
            text = text,
            font = regularFont(),
            fontSize = 12.sp,
            modifier = Modifier
                .padding(1.dp)
                .fillMaxWidth(0.8f),
        )

        isActive?.let {
            Spacer(modifier = Modifier.width(10.dp))
            Canvas(
                modifier = Modifier
                    .wrapContentSize()
                    .fillMaxWidth()
                    .padding(end = 5.dp),
                onDraw = {
                    drawCircle(
                        color = if (isActive) Color.Green else Color.Red,
                        radius = 10f
                    )
                }
            )
        }
    }
}
