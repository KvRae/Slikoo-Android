package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.Feedback
import slikoo.kvrae.slikoo.ui.theme.LightSurface




@Composable
fun RatingCard(feedBack : Feedback) {
    val dateFormat = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    val date = dateFormat.format(feedBack.date)
    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(240.dp)
            .height(140.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(16.dp)
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp))
            {
                AsyncImage(
                    model = painterResource(id = R.drawable.avatar),
                    contentDescription = "avatar",
                    modifier = Modifier.size(40.dp)
                )
                Column( modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = feedBack.providerId, modifier = Modifier.padding(4.dp),
                        style = TextStyle(fontSize = 12.sp)
                    )
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                        RatingBar(
                            onRatingChanged = { feedBack.rate = it },
                            currentRating = feedBack.rate
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = date, modifier = Modifier.padding(6.dp),
                            style = TextStyle(fontSize = 11.sp)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = feedBack.comment,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(fontSize = 12.sp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
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
                tint = if (index < currentRating) LightSurface else Color.Gray,
                modifier = Modifier
                    //.clickable { onRatingChanged(index + 1) }
                    .size(12.dp)
            )
        }
    }
}

@Composable
fun FeedbackRatingBar(
    maxRating: Int = 5,
    currentRating: Int = 0,
    iconsSize : Int = 12,
    onRatingChanged: (Int) -> Unit
) {
    Row(Modifier.padding(4.dp)) {
        repeat(maxRating) { index ->
            Icon(
                imageVector = if (index < currentRating) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = null,
                tint = if (index < currentRating) LightSurface else Color.Gray,
                modifier = Modifier
                    .clickable { onRatingChanged(index + 1) }
                    .size(iconsSize.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UserRatingBar() {
    Column(

    ) {
        Row {
            //AsyncImage(model = , contentDescription = )
            Image(painter = painterResource(
                id =R.drawable.avatar),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(30.dp)
                    .padding(4.dp)
            )

            Column {
                Text(
                    text = "Karam Mannai",
                    fontWeight = FontWeight.Medium,
                    fontSize = 10.sp,
                    modifier = Modifier.padding(end = 4.dp, top = 1.dp, bottom = 0.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
                FeedbackRatingBar(
                    currentRating = 3,
                    onRatingChanged = {},
                    iconsSize = 8
                )
            }


        }
        Text(text = stringResource(id = R.string.welcome_sub_description),
            fontSize = 8.sp,
            fontWeight = FontWeight.Light,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 0.dp, bottom = 0.dp),
        )
        Divider(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )
    }
}
