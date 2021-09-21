package kasem.sm.delightplayground.features.rocket_detail

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun RocketDetailScreen(
    rocketId: Int?
) {

    Text(
        text = "Rocket ID $rocketId",
        color = Color.White
    )
}
