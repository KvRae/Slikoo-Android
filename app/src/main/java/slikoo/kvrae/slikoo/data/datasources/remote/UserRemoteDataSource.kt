package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.dto.ForgetPasswordRequest
import slikoo.kvrae.slikoo.data.datasources.remote.dto.LoginRequest
import slikoo.kvrae.slikoo.data.datasources.remote.dto.RibRequest
import java.io.File
import java.io.IOException

class UserRemoteDataSource {

    suspend fun register(user: User, avatar: File, cin: File): String {
        val avatarRequestBody = avatar.asRequestBody("image/*".toMediaTypeOrNull())
        val cinRequestBody = cin.asRequestBody("image/*".toMediaTypeOrNull())

        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .register(
                    nom = user.nom.toRequestBody("text/plain".toMediaTypeOrNull()),
                    prenom = user.prenom.toRequestBody("text/plain".toMediaTypeOrNull()),
                    email = user.email.toRequestBody("text/plain".toMediaTypeOrNull()),
                    password = user.password.toRequestBody("text/plain".toMediaTypeOrNull()),
                    numtel = user.numtel.toRequestBody("text/plain".toMediaTypeOrNull()),
                    adressepostal = user.adressepostal.toRequestBody("text/plain".toMediaTypeOrNull()),
                    ville = user.ville.toRequestBody("text/plain".toMediaTypeOrNull()),
                    codepostal = user.codepostal.toRequestBody("text/plain".toMediaTypeOrNull()),
                    description = user.description.toRequestBody("text/plain".toMediaTypeOrNull()),
                    avatar = MultipartBody.Part.createFormData("avatar", avatar.name, avatarRequestBody),
                    cinavatar = MultipartBody.Part.createFormData("cinavatar", cin.name, cinRequestBody),
                    sexe = user.sexe.toRequestBody("text/plain".toMediaTypeOrNull())
                )

            when (response.code()) {
                in 200..299 -> {
                    Log.d("Response from server ${response.code()}", response.body().toString())
                    response.body().toString()
                }
                401 -> {
                    Log.d("Response from server 401", response.body().toString())
                    response.body().toString()
                }
                else -> {
                    Log.d("Response from server ${response.code()}", response.body().toString())
                    response.body().toString()
                }
            }
        } catch (e: IOException) {
            Log.d("Network error", e.message.toString())
            e.message.toString()
        } catch (e: Exception) {
            Log.e("Exception in data source", e.toString())
            e.message.toString()
        }
    }


    suspend fun authUser(user : User): String {
        return try {
            val request = LoginRequest(username = user.email, password = user.password)
            val response = RetrofitInstance.getRetrofitInstance().
            create(ApiServices::class.java).login(request)

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
               Log.d("user in data source ok", response.body()?.user.toString())
               response.body()?.user!!

            } else {
                Log.d("user in data source not ok", response.body()?.user.toString())
                User()
            }
        } catch (e: Exception) {
            Log.d("user in data source exception", e.message.toString())
            User()
         }

    }

    suspend fun getUserById(token: String, id: Int) : User {
       return try {
           val retIn = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
           val response = retIn.getUserDetailsById(token= "Bearer $token", id = id)
           if (response.code() == 200) {
                response.body()?.user!!
            } else {
                User()
            }
        } catch (e: Exception) {
            User()
         }

    }

    suspend fun getFeedBackById(token: String, id: Int) : String {
       return try {
           val retIn = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
           val response = retIn.getFeedbackById(token= "Bearer $token", id = id)
           if (response.code() == 200) {
                response.body()?.toString()!!
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
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
                addRib(token = "Bearer $token" , ribRequest = RibRequest(email = user.email, rib = user.RIB?:""))
            if (response.code() == 200) {
                Log.d("Response from server 200", response.body().toString())
                200
            } else if (response.code() == 401) {
                Log.d("Response from server 401", response.body().toString())
                401
            } else {
                Log.d("Response from server 500", response.body().toString())
                500
            }
        } catch (e: Exception) {
            Log.d("response in data source 500 catch", e.message.toString())
            500
        }

    }


}