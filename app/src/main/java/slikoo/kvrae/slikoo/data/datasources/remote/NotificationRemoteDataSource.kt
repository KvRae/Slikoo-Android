package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.Notification

class NotificationRemoteDataSource {

        suspend fun getNotifications(token: String, email: String): MutableList<Notification> {
            val notifications = mutableListOf<Notification>()
            try {
                val response = RetrofitInstance.getRetrofitInstance()
                    .create(ApiServices::class.java)
                    .getNotificationsByEmail(token = "Bearer $token", email = email)
                if (response.isSuccessful)
                    response.body()?.notifications?.let { notifications.addAll(it) }
            }
            catch (e: Exception) {
                Log.e("Notifications Error", e.message.toString())
            }
            return notifications
        }
    }
