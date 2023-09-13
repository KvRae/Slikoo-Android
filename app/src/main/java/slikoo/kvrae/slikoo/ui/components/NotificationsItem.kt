package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.Notification
import slikoo.kvrae.slikoo.ui.theme.LightBackground



@Composable
fun NotificationItem(notification: Notification) {
    Card(
        modifier = Modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp,
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(40.dp),
                        painter = painterResource(id = R.drawable.avatar),
                        contentDescription = "" )
                    Text(text = "notification.fromuser[0].nom",
                        style = TextStyle(color = LightBackground, fontSize = 14.sp, fontWeight = FontWeight.Medium),
                        modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(onClick = { /*TODO*/})
                    {
                        Icon(imageVector = Icons.Default.Close,
                            contentDescription = "",
                            tint = Color.Gray,
                            modifier = Modifier.size(15.dp))
                    }
                }
                Spacer(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
                Text(text = "notification.motif",
                    style = TextStyle(color = LightBackground, fontSize = 12.sp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)
            }
        }
    }
}



//@Composable
//fun NotificationItemContent(notification: Notification) {
//
//}