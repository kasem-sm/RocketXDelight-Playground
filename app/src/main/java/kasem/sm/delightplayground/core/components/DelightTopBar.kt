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

package kasem.sm.delightplayground.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kasem.sm.delightplayground.ui.theme.delightSubHeadingColor

data class MenuItem(
    val icon: Int? = null,
    val iconVector: ImageVector? = null,
    val onClick: () -> Unit
)

@Composable
fun DelightTopBar(
    modifier: Modifier = Modifier,
    titleText: String,
    descriptionText: String,
    menuItems: List<MenuItem>? = null
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            DelightText(
                text = titleText,
                fontSize = 32.sp,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = 4.dp, start = 14.dp)
            )
            DelightText(
                text = descriptionText,
                color = delightSubHeadingColor(),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(start = 14.dp)
            )
        }
        menuItems?.forEach { menuItem ->
            menuItem.icon?.let { icon ->
                DelightIcon(
                    icon = icon,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 14.dp),
                    onClick = {
                        menuItem.onClick()
                    }
                )
            } ?: menuItem.iconVector?.let { iconVector ->
                DelightIcon(
                    icon = iconVector,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(end = 14.dp),
                    onClick = {
                        menuItem.onClick()
                    }
                )
            } ?: throw NullPointerException("How?!")
        }
    }
}
