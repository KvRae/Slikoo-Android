package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Notification
import slikoo.kvrae.slikoo.data.datasources.remote.NotificationRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class NotificationViewModel : ViewModel() {
    val isLoading = mutableStateOf(true)
    val isError = mutableStateOf(false)
    val notifications = MutableLiveData<MutableList<Notification>>(mutableStateListOf())
    private val notificationRepository = NotificationRemoteDataSource()

    fun getNotifications() {
        try {
            isLoading.value = true
            viewModelScope.launch(Dispatchers.IO) {
                val token = TempSession.token
                val email = TempSession.email
                val response = async { notificationRepository.getNotifications(token, email) }
                val result = response.await()
                if (result.isNotEmpty()) {
                    notifications.value?.clear()
                    notifications.value?.addAll(result)
                    notifications.value?.reverse()
                } else {
                    notifications.value?.clear()
                }
            }
        } catch (e: Exception) {
            isError.value = true
            notifications.value?.clear()
        }
        finally {
            isLoading.value = false
        }
    }
}
