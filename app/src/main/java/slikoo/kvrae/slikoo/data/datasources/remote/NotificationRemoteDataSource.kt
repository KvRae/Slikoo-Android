package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.Notification

class NotificationRemoteDataSource {

    suspend fun getNotifications(token: String, email: String, notifications: MutableList<Notification>) {
        val retIn = RetrofitInstance.getRetrofitInstance()
            .create(ApiServices::class.java)
        val response = retIn.getNotificationsByEmail("Bearer $token", email)
        val gson = com.google.gson.Gson()

        Log.wtf("Notifications List DS gSon", gson.toJson(response.body()?.notifications))
        try {
            if (response.isSuccessful) {
                response.body()?.notifications?.let { notifications.addAll(it) }
            }
        } catch (e: Exception) {
            Log.e("Notifications Error", e.message.toString())
        }

    }


}