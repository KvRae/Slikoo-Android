package slikoo.kvrae.slikoo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Notification
import slikoo.kvrae.slikoo.data.datasources.remote.NotificationRemoteDataSource


class NotificationViewModel : ViewModel() {
    private val notifcationRepository = NotificationRemoteDataSource()
    private var notifications = mutableListOf<Notification>()

    fun getNotifications(email: String, token: String): List<Notification> {

        viewModelScope.launch(Dispatchers.IO) {
            val list  = notifcationRepository.getNotifications(token, email)
            Log.d("NotificationViewModel res", "getNotifications: $list")
            if (list != null) {
                notifications.addAll(list)
                Log.d("NotificationViewModel notfications", "getNotifications: $notifications")
            }
        }
        return notifications
    }

    fun removeNotification(notification: Notification) {
        //notifications.remove(notification)
    }


}
