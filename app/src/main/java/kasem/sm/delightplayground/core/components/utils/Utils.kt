package kasem.sm.delightplayground.core.components.utils

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import kasem.sm.delightplayground.R

@Composable
fun draw(@DrawableRes icon: Int) = painterResource(id = icon)

@Composable
fun delightFont() = FontFamily(Font(R.font.header, weight = FontWeight.Bold))

@Composable
fun regularFont() = FontFamily(Font(R.font.medium, weight = FontWeight.Medium))
