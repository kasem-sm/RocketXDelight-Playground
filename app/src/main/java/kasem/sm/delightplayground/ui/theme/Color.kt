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

package kasem.sm.delightplayground.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Composable
fun delightBackground(): Brush {
    return if (isSystemInDarkTheme()) {
        Brush.verticalGradient(
            colors = listOf(Color.Black, Color(0xFF01060A))
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(Color.White, Color.White)
        )
    }
}

@Composable
fun delightTextColor(): Color {
    return MaterialTheme.colors.onBackground
}

@Composable
fun delightSubHeadingColor(): Color {
    return if (isSystemInDarkTheme()) {
        Color(0xFFBE3320)
    } else {
        Color(0xFFDA1E04)
    }
}

@Composable
fun delightBottomSheetColor(): Color {
    return if (isSystemInDarkTheme()) {
        Color(0xFF161616)
    } else {
        Color(0xFFE0DEDE)
    }
}
