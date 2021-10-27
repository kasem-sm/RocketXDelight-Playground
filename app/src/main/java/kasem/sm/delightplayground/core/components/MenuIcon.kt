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

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kasem.sm.delightplayground.core.components.utils.draw

@Composable
fun DelightIcon(
    modifier: Modifier = Modifier,
    icon: Int,
    size: Dp = 25.dp,
    contentDescription: String? = null,
    addSpace: Boolean = false,
    tint: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    onClick: () -> Unit = {},
) {
    Row {
        Icon(
            painter = draw(icon),
            contentDescription = contentDescription,
            modifier = modifier
                .size(size)
                .clip(CircleShape)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = rememberRipple(bounded = true),
                    onClick = onClick
                ),
            tint = tint
        )
        if (addSpace) {
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Composable
fun DelightIcon(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    size: Dp = 25.dp,
    contentDescription: String? = null,
    addSpace: Boolean = false,
    onClick: () -> Unit = {},
    tint: Color = MaterialTheme.colorScheme.secondary
) {
    Icon(
        imageVector = icon,
        contentDescription = contentDescription,
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = onClick
            ),
        tint = tint
    )
    if (addSpace) {
        Spacer(modifier = Modifier.width(10.dp))
    }
}
