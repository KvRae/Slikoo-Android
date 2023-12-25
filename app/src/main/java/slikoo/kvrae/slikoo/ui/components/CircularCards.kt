package slikoo.kvrae.slikoo.ui.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.ui.theme.LightBackground
import slikoo.kvrae.slikoo.ui.theme.LightError
import slikoo.kvrae.slikoo.ui.theme.LightPrimary


@Composable
fun CircularMealCard(
    image: String = "banner",
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = Modifier
            .size(100.dp)
            .clip(shape = CircleShape)
            .clickable { onClick()},
        elevation = 8.dp
    ) {
        AsyncImage(
            model = image,
            contentDescription = "banner",
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun MealCardWrapper(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    title : String = "Meal Name",
    image : String = "banner",
    color : Color = LightPrimary,
    icon : ImageVector = Icons.Filled.ThumbUp,

) {
    Column(
        modifier = modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {
            CircularMealCard(
                image = image,
                onClick = onClick
            )
            Icon(
                imageVector = icon,
                contentDescription = "star",
                tint = LightError,
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .padding(end = 8.dp) // Add padding for some space from the edges
                    .background(color, CircleShape)
                    .padding(4.dp)
                    .size(20.dp)


            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            color = LightBackground,
            modifier = Modifier
                .width(100.dp),
        )

    }

}