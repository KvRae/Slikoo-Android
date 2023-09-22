package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.valentinilk.shimmer.shimmer
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.Notification
import slikoo.kvrae.slikoo.ui.theme.LightBackground



@Composable
fun NotificationItem(notification: Notification) {
    val model : Any = if (notification.fromuser[0].avatar.isEmpty()) painterResource(id = R.drawable.avatar) else "https://slikoo.com/UsersProfileImgs/${notification.fromuser[0].avatar}"
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    model = model,
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(
                        text = "De " + notification.fromuser[0].nom + " " + notification.fromuser[0].prenom,
                        style = TextStyle(
                            color = LightBackground,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Text(text = notification.motif ,
                        modifier = Modifier.padding(start = 8.dp),
                        style = TextStyle(color = LightBackground, fontSize = 12.sp),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis)
                }
                //Spacer(modifier = Modifier.weight(1f))
                /*IconButton(onClick = { /*TODO*/})
                {
                    Icon(imageVector = Icons.Default.Close,
                        contentDescription = "",
                        tint = Color.Gray,
                        modifier = Modifier.size(15.dp))
                }*/
            }
        }
    }
}



@Composable
fun NotificationItemShimmer() {
Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp,
    ) {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp).shimmer()
                    .clip(CircleShape)
                    .background(color = Color.Gray)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Column {
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, end = 64.dp)
                        .fillMaxWidth().shimmer()
                        .height(20.dp)
                        .background(color = Color.Gray)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Box(
                    modifier = Modifier
                        .padding(start = 8.dp, top = 8.dp)
                        .fillMaxWidth().shimmer()
                        .height(20.dp)
                        .background(color = Color.Gray)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

}





