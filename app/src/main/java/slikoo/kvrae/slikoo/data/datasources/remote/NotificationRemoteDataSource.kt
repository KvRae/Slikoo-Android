package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.Notification

class NotificationRemoteDataSource {

        suspend fun getNotifications(token: String, email: String): List<Notification> {
            try {
                val response = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
                    .getNotificationsByEmail("Bearer $token", email)

                return runCatching {
                    if (response.isSuccessful) {
                        response.body()?.notifications?.let {
                            Log.d("Notifications List size DS", it.size.toString())
                            return it
                        }
                    } else {
                        Log.e("Notifications Error", response.errorBody()?.string() ?: "Unknown error")
                    }
                    listOf(Notification()) // Return empty list or handle error case appropriately
                }.getOrElse {
                    // Handle exceptions more appropriately, you might want to notify the ViewModel
                    Log.e("Notifications Exception", "Failed to get notifications", it)
                    emptyList()
                }

            } catch (e: Exception) {
                // Log exceptions in the outer catch block only if necessary
                Log.e("Notifications Exception", "Failed to get notifications", e)
                return emptyList()
            }
        }
    }
