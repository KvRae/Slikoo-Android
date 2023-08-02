package slikoo.kvrae.slikoo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import slikoo.kvrae.slikoo.data.models.Notification
import slikoo.kvrae.slikoo.viewmodel.NotificationViewModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotificationItem(content : @Composable () -> Unit) {
    Card(
        modifier = Modifier.padding(12.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        enabled = false,
        onClick = { /*TODO*/ }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}



@Composable
fun NotificationItemContent(notification: Notification ) {

    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Image(
                modifier = Modifier.weight(0.2f),
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "" )
            Text(text = notification.title,
                style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.Bold),
                modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { NotificationViewModel().removeNotification(notification = notification) }) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier.size(15.dp))
            }
        }
        Text(text = notification.description,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis)
    }
}