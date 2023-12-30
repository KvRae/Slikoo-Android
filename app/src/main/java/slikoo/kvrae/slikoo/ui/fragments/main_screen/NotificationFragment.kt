package slikoo.kvrae.slikoo.ui.fragments.main_screen


import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.ui.components.NotificationItem
import slikoo.kvrae.slikoo.ui.components.NotificationItemShimmer
import slikoo.kvrae.slikoo.ui.pages.TextWithImageScreen
import slikoo.kvrae.slikoo.ui.theme.LightSecondary
import slikoo.kvrae.slikoo.viewmodels.NotificationViewModel

@Composable
fun NotificationScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    val notificationViewModel: NotificationViewModel = viewModel()
    DisposableEffect(key1 = notificationViewModel.notifications.value?.size) {
        notificationViewModel.getNotifications()
        onDispose { }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(LightSecondary),
        contentAlignment = Alignment.Center
    ) {
        when {
            notificationViewModel.isLoading.value -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(6) {
                        NotificationItemShimmer()
                    }
                }
            }
            notificationViewModel.notifications.value!!.isNotEmpty() -> {
                SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = notificationViewModel.isLoading.value),
                    onRefresh = { notificationViewModel.getNotifications()}) {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .scrollable(scrollState, Orientation.Vertical),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        items(notificationViewModel.notifications.value?.size ?: 0, key = { it }) {
                            NotificationItem(
                                notification = notificationViewModel.notifications.value!![it]
                                    ?: return@items
                            )
                        }
                    }
                }
            }

            notificationViewModel.notifications.value!!.isEmpty() && !notificationViewModel.isLoading.value -> {
                TextWithImageScreen(
                    imageVector = ImageVector.vectorResource(R.drawable.no_notifications),
                    text = stringResource(id = R.string.no_notifications),
                    background = LightSecondary
                )
            }

        }
    }
}
