package kasem.sm.delightplayground.features.rocket_list.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kasem.sm.delightplayground.datasource.Rocket
import kasem.sm.delightplayground.ui.theme.CardColorDark

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RocketListItem(
    rocket: Rocket,
    imageLoader: ImageLoader,
    onItemClicked: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .clip(
                RoundedCornerShape(12.dp)
            )
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true),
                onClick = {
                    onItemClicked(rocket.id.toInt())
                }
            ),
        border = BorderStroke(
            width = 1.dp,
            brush = Brush.verticalGradient(
                colors = listOf(Color.LightGray.copy(alpha = 0.25f), Color.Transparent)
            )
        ),
        shape = RoundedCornerShape(12.dp),
        backgroundColor = CardColorDark
    ) {
        val painter = rememberImagePainter(
            data = rocket.image,
            imageLoader = imageLoader
        )

        Row {
            Column {
                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .height(180.dp)
                        .fillMaxWidth()
                )

                Text(
                    text = "► ${rocket.description}",
                    letterSpacing = 0.2.sp,
                    lineHeight = 20.sp,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(15.dp)
                )
            }
        }
    }
}
