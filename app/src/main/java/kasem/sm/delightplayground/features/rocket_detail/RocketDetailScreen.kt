package kasem.sm.delightplayground.features.rocket_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kasem.sm.delightplayground.ui.theme.CardColorDark
import kasem.sm.delightplayground.ui.theme.CardColorLight

@Composable
fun RocketDetailScreen(
    rocketId: Int?
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) CardColorDark else CardColorLight)
    ) {
        Text(
            text = "Rocket ID $rocketId",
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}
