package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.dto.ForgetPasswordRequest
import slikoo.kvrae.slikoo.data.datasources.remote.dto.LoginRequest
import slikoo.kvrae.slikoo.data.datasources.remote.dto.RibRequest
import java.io.File

class UserRemoteDataSource {

    suspend fun register(user: User, avatar: File, cin: File ): Int {
        val avatarRequestBody = avatar.asRequestBody("image/*".toMediaTypeOrNull())
        val cinRequestBody = cin.asRequestBody("image/*".toMediaTypeOrNull())
       return try {

            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .register(
                    nom = MultipartBody.Part.createFormData("nom", user.nom),
                    prenom = MultipartBody.Part.createFormData("prenom", user.prenom),
                    email = MultipartBody.Part.createFormData("email", user.email),
                    password = MultipartBody.Part.createFormData("password", user.password),
                    numtel = MultipartBody.Part.createFormData("numtel", user.numtel),
                    adressepostal = MultipartBody.Part.createFormData("adressepostal", user.codepostal),
                    ville = MultipartBody.Part.createFormData("ville", user.ville),
                    codepostal = MultipartBody.Part.createFormData("codepostal", user.codepostal),
                    description = MultipartBody.Part.createFormData("description", user.description),
                    avatar = MultipartBody.Part.createFormData("avatar", avatar.name, avatarRequestBody),
                    cinavatar = MultipartBody.Part.createFormData("cinavatar", cin.name, cinRequestBody),
                    sexe = MultipartBody.Part.createFormData("sex", user.sexe),
                )
            if (response.code() == 201) {
                Log.d("Response from server 200", response.body().toString())
                201
            } else if (response.code() == 401) {
                Log.d("Response from server 401", response.body().toString())
                401
            } else {
                Log.d("Response from server 500", response.body().toString())
                500
            }
        }
        catch (e: Exception) {
            Log.d("response in data source 500 catch", e.message.toString())
            500
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