package slikoo.kvrae.slikoo.ui.fragments.main_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.NotificationItem
import slikoo.kvrae.slikoo.ui.components.NotificationItemShimmer
import slikoo.kvrae.slikoo.ui.pages.TextWithImageScreen
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.NotificationViewModel

@Composable
fun NotificationScreen(navController: NavController) {
    val notificationViewModel: NotificationViewModel = viewModel()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        when {
            notificationViewModel.isLoading.value && notificationViewModel.notifications.isEmpty() -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(6) {
                        NotificationItemShimmer()
                    }
                }
            }
            notificationViewModel.notifications.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .scrollable(scrollState, Orientation.Vertical),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(notificationViewModel.notifications.size) {
                        NotificationItem(notification = notificationViewModel.notifications[it])
                    }
                }
            }
            notificationViewModel.notifications.isEmpty() && !notificationViewModel.isLoading.value -> {
                TextWithImageScreen(
                    imageVector = Icons.Rounded.Notifications,
                    text =  stringResource(id = R.string.no_notifications)
                )
            }
        }
    }
}
