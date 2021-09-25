package kasem.sm.delightplayground.features.rocket_detail.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Description
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kasem.sm.delightplayground.core.components.DelightIcon
import kasem.sm.delightplayground.core.components.DelightText
import kasem.sm.delightplayground.core.components.utils.regularFont
import kasem.sm.delightplayground.datasource.Rocket

@Composable
fun RocketDescription(
    rocket: Rocket
) {
    Column {
        Row {
            DelightIcon(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 16.dp, top = 16.dp, bottom = 10.dp),
                icon = Icons.Outlined.Description,
                size = 18.dp,
                addSpace = true
            )

            DelightText(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(top = 16.dp, bottom = 10.dp),
                text = "Swipe up to read about ${rocket.rocketTitle}.",
                font = regularFont(),
                fontSize = 14.sp
            )
        }

        DelightText(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(
                    horizontal = 18.dp,
                    vertical = 10.dp
                ),
            text = rocket.description,
            font = regularFont()
        )
    }
}
