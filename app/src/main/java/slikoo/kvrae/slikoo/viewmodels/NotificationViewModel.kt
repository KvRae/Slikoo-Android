package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Notification
import slikoo.kvrae.slikoo.data.datasources.remote.NotificationRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession


class NotificationViewModel : ViewModel() {
    private val notificationRepository = NotificationRemoteDataSource()
    var notifications = mutableStateListOf<Notification>()
    var isLoading = true

    init {
        getNotifications(email = TempSession.email, token = TempSession.token, notifications = notifications)
    }

     private fun getNotifications(email: String, token: String, notifications: MutableList<Notification>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                async { isLoading = true }.await()
                async {notificationRepository.getNotifications(token =token, email =  email, notifications= notifications)}.await()
                async { isLoading = false }.await()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



}
