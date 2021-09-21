package kasem.sm.delightplayground.features.rocket_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import kasem.sm.delightplayground.R
import kasem.sm.delightplayground.features.rocket_list.components.RocketList
import kasem.sm.delightplayground.ui.theme.CardColorDark
import kasem.sm.delightplayground.ui.theme.CardColorLight

@Composable
fun RocketListScreen(
    state: RocketState,
    imageLoader: ImageLoader,
    provokeGetRocket: () -> Unit,
    onItemClick: (Int) -> Unit,
    openDelightRepo: () -> Unit
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
                actions = {
                    DelightGithubRepo { openDelightRepo() }
                },
                backgroundColor = if (isSystemInDarkTheme()) CardColorDark else CardColorLight,
                elevation = 0.dp
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

@Composable
fun DelightGithubRepo(onClick: () -> Unit) {
    val icon = painterResource(R.drawable.ic_github)
    Icon(
        icon,
        "Github",
        Modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(50.dp))
            .clickable(onClick = onClick)
    )
}
