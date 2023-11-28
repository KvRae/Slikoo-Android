package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import slikoo.kvrae.slikoo.data.datasources.entities.Notification
import slikoo.kvrae.slikoo.data.datasources.remote.NotificationRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class NotificationViewModel : ViewModel() {
    var isLoading = mutableStateOf(true)
    var isError = mutableStateOf(false)
    var notifications = mutableStateListOf<Notification>()
    private val notificationRepository = NotificationRemoteDataSource()

    init {
        getNotifications()
    }

    private fun getNotifications() {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    notifications.clear()
                    notifications.addAll(notificationRepository.getNotifications(
                        token = TempSession.token,
                        email = TempSession.email
                    ))
                }
                isLoading.value = false
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading.value = false
                isError.value = true
            }
        }
    }
}
