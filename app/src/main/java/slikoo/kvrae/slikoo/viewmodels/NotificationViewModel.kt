package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
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
        viewModelScope.launch(Dispatchers.IO) {
            val token = TempSession.token
            val email = TempSession.email
            val response = async { notificationRepository.getNotifications(token, email) }
            val result = response.await()
            if (result.isNotEmpty()) {
                notifications.addAll(result)
                notifications.reverse()
                isLoading.value = false
            } else {
                isError.value = true
                isLoading.value = false
            }
        }
    }
}
