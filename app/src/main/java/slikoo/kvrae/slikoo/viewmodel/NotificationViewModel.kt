package slikoo.kvrae.slikoo.viewmodel

import android.util.Log
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
    val notifications = mutableListOf<Notification>()

    init {
        getNotifications(TempSession.email, TempSession.token, notifications)
    }

    private fun getNotifications(email: String, token: String, notifications: MutableList<Notification>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                async {notificationRepository.getNotifications(token, email, notifications)}.await()
                async { Log.d("Notifications List size VM", notifications.size.toString()) }.await()

            } catch (e: Exception) {
               Log.e("Notifications Error Vm", e.message.toString())
            }
        }
    }



}
