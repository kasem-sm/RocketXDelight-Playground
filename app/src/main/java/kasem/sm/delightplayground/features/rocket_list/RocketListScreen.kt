package kasem.sm.delightplayground.features.rocket_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import kasem.sm.delightplayground.features.rocket_list.components.RocketList
import kasem.sm.delightplayground.ui.theme.CardColorDark

@Composable
fun RocketListScreen(
    state: RocketState,
    imageLoader: ImageLoader,
    provokeGetRocket: () -> Unit,
    onItemClick: (Int) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column {
                        Text(
                            text = "Delight Playground",
                            fontSize = 16.sp,
                            style = MaterialTheme.typography.h4,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Ft. SpaceX API",
                            fontSize = 10.sp,
                            style = MaterialTheme.typography.h6,
                        )
                    }
                },
                backgroundColor = CardColorDark
            )
        },
    ) {
        RocketList(
            state = state,
            imageLoader = imageLoader,
            provokeGetRocket = provokeGetRocket,
            onItemClicked = onItemClick
        )
    }
}
