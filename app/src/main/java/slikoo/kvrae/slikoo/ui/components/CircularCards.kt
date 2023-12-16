package slikoo.kvrae.slikoo.ui.components
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R


@Composable
fun CircularMealCard() {

    Surface(
        modifier = Modifier
            .size(100.dp)
            .clip(shape = CircleShape)
            .clickable { },
        elevation = 8.dp
    ) {
        AsyncImage(
            model = R.drawable.banner,
            contentDescription = "banner",
            contentScale = ContentScale.Crop,
        )
        Box(
            Modifier
                .fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = "star",
                tint = Color.Black,
                modifier = Modifier
                    .align(alignment = Alignment.BottomEnd)
                    .size(20.dp)

            )
        }
    }
}

@Composable
fun MealCardWrapper(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    title : String = "Meal Name",
    image : String = "banner"
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularMealCard()
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold)
    }

}