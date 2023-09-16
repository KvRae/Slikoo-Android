package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.Notification

class NotificationRemoteDataSource {

    suspend fun getNotifications(token: String, email: String, notifications: MutableList<Notification>) {
        val response = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
            .getNotificationsByEmail("Bearer $token", email)

        try {
            if (response.isSuccessful) {
                response.body()?.notifications?.let { notifications.addAll(it) }
                Log.d("Notifications List size DS", notifications.size.toString())
            } else {
                notifications.add(Notification())
                Log.e("Notifications Error", response.errorBody().toString())
            }
        } catch (e: Exception) {
            Log.e("Notifications Exception", e.message.toString())
        }

    }


}