package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.theme.DefautBlueElement


@OptIn(ExperimentalMaterialApi::class)
@Preview
@Composable
fun RatingCard() {
    val rating = remember {
        mutableStateOf(3)
    }
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        onClick = { /*TODO*/ }
    ) {
        Column(
            modifier = Modifier
                .background(White).padding(16.dp)
        ) {
            Row(modifier = Modifier.padding(4.dp))
            {
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "avatar",
                    modifier = Modifier.size(40.dp)
                )
                Column {
                    Text(
                        text = "Name", modifier = Modifier.padding(4.dp),
                        style = TextStyle(fontSize = 10.sp)
                    )
                    Row {
                        RatingBar(
                            onRatingChanged = { rating.value = it },
                            currentRating = rating.value
                        )
                        Spacer(modifier = Modifier.padding(8.dp))
                        Text(
                            text = "23/12/2021", modifier = Modifier.padding(4.dp),
                            style = TextStyle(fontSize = 8.sp)
                        )
                    }
                }
            }
            Text(
                text = "Description",
                style = TextStyle(fontSize = 10.sp),
                modifier = Modifier.padding(4.dp)
            )
        }

    }
}

@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: Int = 0,
    onRatingChanged: (Int) -> Unit
) {
    Row(Modifier.padding(4.dp)) {
        repeat(maxRating) { index ->
            Icon(
                imageVector = if (index < currentRating) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = null,
                tint = if (index < currentRating) DefautBlueElement else Color.Gray,
                modifier = Modifier
                    .clickable { onRatingChanged(index + 1) }
                    .size(12.dp)
            )
        }
    }
}
