package slikoo.kvrae.slikoo.data.datasources.remote

import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.LoginRequest
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.entities.Notification
import slikoo.kvrae.slikoo.data.entities.User

class UserRemoteDataSource {

    suspend fun login(user : User): String {
        val retIn = RetrofitInstance
            .getRetrofitInstance()
            .create(ApiServices::class.java)
        val request = LoginRequest(
            username = user.email,
            password = user.password)
        return try {
            val response = retIn.login(request)
            if (response.isSuccessful) {
                response.body()?.token.toString()
            } else {
                response.code().toString()
            }
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    suspend fun getUserByEmail(token: String) : User {
        val retIn = RetrofitInstance
            .getRetrofitInstance()
            .create(ApiServices::class.java)
        try {
            val response = retIn.getUserByEmail(token= token , user =  User())
            if (response.code() == 200) {
                return response.body()!!
            }
        } catch (e: Exception) {
            return User()
        }
        return User()
    }

    suspend fun getNotificationsByEmail(email: String): MutableList<Notification> {
        // Declare a retrofit instance
        val retIn= RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)

        try {
            val response = retIn.getNotificationsByEmail(email)
            if (response.code() == 200) {
                return response.body()?.notification as MutableList<Notification>
            }

        }
        catch (e: Exception) {
            return mutableListOf<Notification>()
        }
        return mutableListOf<Notification>()
    }

    suspend fun addRib(user : User): String {
        val retIn = RetrofitInstance
            .getRetrofitInstance()
            .create(ApiServices::class.java)

        return try {
            val response = retIn.addRib(user)
            if (response.code() == 200) {
                response.body().toString()
            } else if (response.code() == 401) {
                response.body().toString()
            } else {
                response.body().toString() + " " + response.code().toString()
            }
        } catch (e: Exception) {
            e.message.toString()
        }

    }

}