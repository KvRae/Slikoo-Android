package slikoo.kvrae.slikoo.ui.fragments


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.ui.components.NotificationItem
import slikoo.kvrae.slikoo.ui.components.NotificationItemContent
import slikoo.kvrae.slikoo.viewmodel.NotificationViewModel



@Composable
fun NotificationScreen(navController: NavController) {
    val notifications = remember { NotificationViewModel().getNotifications()}
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(scrollState, Orientation.Vertical),
            horizontalAlignment = Alignment.CenterHorizontally) {
            items(notifications.size) {
                NotificationItem(){
                    NotificationItemContent(
                        profileImage = notifications[it].profileImage,
                        title = notifications[it].title,
                        description = notifications[it].description,
                        time = notifications[it].time
                    )
                }
            }

    }
}
}

