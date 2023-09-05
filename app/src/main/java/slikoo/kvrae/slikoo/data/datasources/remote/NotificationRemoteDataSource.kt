package slikoo.kvrae.slikoo.data.datasources.remote

import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.Notification

class NotificationRemoteDataSource {

    suspend fun getNotifications(token: String, email: String): List<Notification>? {
        val retIn = RetrofitInstance.getRetrofitInstance()
            .create(ApiServices::class.java)

        val response = retIn.getNotificationsByEmail("Bearer $token", email)
        return if (response.code() == 200) {
            response.body()?.notification
        } else {
            mutableListOf()
        }

    }


}