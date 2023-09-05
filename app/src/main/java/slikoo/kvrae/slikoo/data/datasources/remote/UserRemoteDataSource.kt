package slikoo.kvrae.slikoo.data.datasources.remote

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.LoginRequest
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.entities.Notification
import java.io.File

class UserRemoteDataSource {

    suspend fun createUser(user: User, avatar : File, cinAvatar: File){

        val retIn = RetrofitInstance.getRetrofitInstance()
            .create(ApiServices::class.java)

        val avatarPart= MultipartBody.Part.createFormData("avatar",
            avatar.name,
            avatar.asRequestBody("image/*".toMediaTypeOrNull())
        )
        val cinAvatarPart = MultipartBody.Part.createFormData("cinAvatar",
            cinAvatar.name,
            cinAvatar.asRequestBody("image/*".toMediaTypeOrNull())
        )

        try {
            val response = retIn.register(user,avatarPart, cinAvatarPart)
            if (response.code() == 201) {
                response.body().toString()
            } else {
                response.body().toString() + " " + response.code().toString()
            }
        } catch (e: Exception) {
            e.message.toString()
        }
    }

    suspend fun authUser(user : User): String {
        return try {
            val request = LoginRequest(username = user.email,
                password = user.password)

            val retIn = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)

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

    suspend fun getUserByEmail(token: String, email: String) : User {
        try {
            val retIn = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
            val response = retIn.getUserByEmail(token= "Bearer $token", email = email)
            if (response.code() == 200) {
                return response.body()!!.user
            }
        } catch (e: Exception) {
            return User()
        }
        return User()
    }

    suspend fun getNotificationsByEmail(token: String, email: String): MutableList<Notification> {
        // Declare a retrofit instance
        val retIn= RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)

        try {
            val response = retIn.getNotificationsByEmail("Bearer $token", email)
            if (response.code() == 200) {
                return response.body()?.notification as MutableList<Notification>
            }

        }
        catch (e: Exception) {
            return mutableListOf<Notification>()
        }
        return mutableListOf<Notification>()
    }

    suspend fun resetPassword(user : User): String {
        val retIn = RetrofitInstance
            .getRetrofitInstance()
            .create(ApiServices::class.java)

        return try {
            val response = retIn.resetPassword(user)
            if (response.code() == 200) {
                "Email sent"
            } else if (response.code() == 400) {
                "No email found"
            } else {
                response.body().toString() + " " + response.code().toString()
            }
        } catch (e: Exception) {
            e.message.toString()
        }

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