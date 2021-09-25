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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kasem.sm.delightplayground.core.components.utils.draw
import kasem.sm.delightplayground.ui.theme.delightTextColor

@Composable
fun DelightIcon(
    modifier: Modifier = Modifier,
    icon: Int,
    size: Dp = 25.dp,
    contentDescription: String? = null,
    addSpace: Boolean = false,
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
            tint = delightTextColor().copy(alpha = 0.85f)
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
        tint = delightTextColor().copy(alpha = 0.85f)
    )
    if (addSpace) {
        Spacer(modifier = Modifier.width(10.dp))
    }
}
