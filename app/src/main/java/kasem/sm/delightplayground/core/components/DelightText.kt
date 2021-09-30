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
