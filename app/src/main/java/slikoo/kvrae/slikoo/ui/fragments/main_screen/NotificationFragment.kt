package slikoo.kvrae.slikoo.ui.fragments.main_screen


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
import androidx.lifecycle.viewmodel.compose.viewModel
import slikoo.kvrae.slikoo.ui.components.NotificationItem
import slikoo.kvrae.slikoo.ui.components.NotificationItemContent
import slikoo.kvrae.slikoo.viewmodel.NotificationViewModel
import slikoo.kvrae.slikoo.viewmodel.SignInViewModel


@Composable
fun NotificationScreen() {
    val signInViewModel : SignInViewModel = viewModel()
    val notifications = remember { NotificationViewModel().getNotifications(token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpYXQiOjE2OTM5MTA1MjUsImV4cCI6MTY5MzkxNDEyNSwicm9sZXMiOlsiUk9MRV9VU0VSIl0sInVzZXJuYW1lIjoiaGFtemFiZW5tYWhtb3VkOTg5OEBnbWFpbC5jb20ifQ.fNf0wV9n_BAzdFA2FtO6pdzLoH8eioeoRh3SaJdCM-NDxUD_CMbH3qs9ZrYSOLkKrXpDjnTvN1kCFExmusZzM99PNbxdM2QjOpiJm3fJYka5viXxKr9sMMgT8d4ewN-tn_XCp-eBvWAu7QMdJOkRB3kp8EzZA-7JBKDY2nzF9dU", email= "hamzabenmahmoud9898@gmail.com") }
    val scrollState = rememberScrollState()
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .scrollable(scrollState, Orientation.Vertical),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(notifications.size) {
                NotificationItem {
                    NotificationItemContent(
                        notification = notifications[it],
                    )
                }
            }
        }
    }
}

