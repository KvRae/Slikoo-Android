package slikoo.kvrae.slikoo.viewmodels

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
    var notifications = mutableListOf<Notification>()
    var isLoading = false

    init {
        getNotifications(email = TempSession.email,token = TempSession.token, notifications =  notifications)
        Log.d("Notifications List size VM", notifications.size.toString())
    }

    private fun getNotifications(email: String, token: String, notifications: MutableList<Notification>) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                async {notificationRepository.getNotifications(token =token, email =  email, notifications= notifications)}.await()
                Log.d("Notifications List size VM", notifications.size.toString())
            } catch (e: Exception) {
               Log.e("Notifications Error Vm", e.message.toString())
            }
        }
    }



}
