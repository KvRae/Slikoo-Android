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
        try {
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
                "1"
            }
        } catch (e: Exception) {
           ""
        }
    }

    suspend fun getUserByEmail(token: String, email: String) : User {
       return try {
           val retIn = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
           val response = retIn.getUserByEmail(token= "Bearer $token", email = email)

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

    suspend fun addRib(user : User, token: String): Int {

        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java).
                addRib(token = "Bearer $token" , email = user.email, rib = user.RIB)
            if (response.code() == 200) {
                200
            } else if (response.code() == 401) {
                401
            } else {
                500
            }
        } catch (e: Exception) {
            500
        }

    }


}