package kasem.sm.delightplayground.core.components

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import kasem.sm.delightplayground.core.components.utils.delightFont
import kasem.sm.delightplayground.ui.theme.delightTextColor

@Composable
fun DelightText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 12.sp,
    font: FontFamily = delightFont(),
    color: Color = delightTextColor(),
    letterSpacing: TextUnit = 0.5.sp
) {
    Text(
        text = text,
        fontSize = fontSize,
        color = color,
        fontFamily = font,
        textAlign = TextAlign.Start,
        letterSpacing = letterSpacing,
        modifier = modifier
    )
}
