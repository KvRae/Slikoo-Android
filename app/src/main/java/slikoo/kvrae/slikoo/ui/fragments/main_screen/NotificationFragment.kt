package slikoo.kvrae.slikoo.ui.fragments.main_screen


import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.NotificationItem
import slikoo.kvrae.slikoo.ui.pages.LoadingScreen
import slikoo.kvrae.slikoo.ui.pages.TextElementScreen
import slikoo.kvrae.slikoo.viewmodels.NotificationViewModel


@Composable
fun NotificationScreen() {
    val notificationViewModel : NotificationViewModel = viewModel()
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Log.d("Notifications List size Fragment", notificationViewModel.notifications.size.toString())

        if (notificationViewModel.notifications.isNotEmpty()) {
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

        if (notificationViewModel.isLoading)
            LoadingScreen()

        if (notificationViewModel.notifications.isEmpty() && !notificationViewModel.isLoading)
            TextElementScreen(text = stringResource(id = R.string.no_element_found))
    }
}

