package slikoo.kvrae.slikoo.ui.fragments.main_screen


import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import slikoo.kvrae.slikoo.ui.components.NotificationItem
import slikoo.kvrae.slikoo.ui.pages.LoadingScreen
import slikoo.kvrae.slikoo.viewmodel.NotificationViewModel


@Composable
fun NotificationScreen() {
    val notificationVM : NotificationViewModel = viewModel()
    val notifications by remember {
        mutableStateOf(notificationVM.notifications)
    }
    val scrollState = rememberScrollState()
    val isNotificationEmpty by remember {
        mutableStateOf(notificationVM.notifications.isNotEmpty())
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = notifications.size.toString())
        if (!isNotificationEmpty) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(scrollState, Orientation.Vertical),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(notifications.size) {
                NotificationItem(notification = notifications[it])
            }

        }}
        else {
            LoadingScreen()
        }
    }
}

