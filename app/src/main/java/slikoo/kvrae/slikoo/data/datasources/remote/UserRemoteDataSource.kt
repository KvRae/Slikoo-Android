package slikoo.kvrae.slikoo.data.datasources.remote

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.ForgetPasswordRequest
import slikoo.kvrae.slikoo.data.api.LoginRequest
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.User
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
        val retIn = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
        val response = retIn.getUserByEmail(token= "Bearer $token", email = email)
         return try {
            if (response.code() == 200) {
                response.body()?.user!!
            } else {
                User()
            }
        } catch (e: Exception) {
            User()
         }

    }

    suspend fun forgotPassword(email : String): Int {
        return try {
            val forgetPasswordRequest = ForgetPasswordRequest(email = email)
            val response =  RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .forgetPassword(forgetPasswordRequest)
            return response.code()
            } catch (e: Exception) {
            500
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