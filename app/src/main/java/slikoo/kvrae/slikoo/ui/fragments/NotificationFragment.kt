package slikoo.kvrae.slikoo.ui.fragments

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.NotificationItem
import slikoo.kvrae.slikoo.ui.components.NotificationItemContent


@Composable
fun NotificationScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 50.dp, bottom = 50.dp),
        contentAlignment = Alignment.Center
    ){
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(scrollState, Orientation.Vertical),
            horizontalAlignment = Alignment.CenterHorizontally) {
            items(10) {
                NotificationItem(){
                    NotificationItemContent(
                        profileImage = R.drawable.avatar,
                        title = "karam Mannai",
                        description = "hello world",
                        time = "Time"
                    )
                }
            }
    }
}
}