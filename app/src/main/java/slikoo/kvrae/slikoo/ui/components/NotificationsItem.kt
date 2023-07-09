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
import androidx.compose.ui.unit.dp
import slikoo.kvrae.slikoo.R


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun NotificationItem(content : @Composable () -> Unit) {
    Card(
        modifier = Modifier.padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        onClick = { /*TODO*/ }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }

}



@Composable
fun NotificationItemContent(profileImage : Int , title : String, description : String, time : String ) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Image(
                modifier = Modifier.weight(0.2f),
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "" )
            Text(text = title, modifier = Modifier.padding(start = 8.dp))
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.Close,
                    contentDescription = "",
                    tint = Color.Gray,
                    modifier = Modifier.size(15.dp))
            }
        }
        Text(text = description)
    }
}