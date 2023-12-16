package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.dto.ForgetPasswordRequest
import slikoo.kvrae.slikoo.data.datasources.dto.LoginRequest
import slikoo.kvrae.slikoo.data.datasources.dto.RibRequest
import slikoo.kvrae.slikoo.data.datasources.dto.UpdatePasswordRequest
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.utils.TempSession.Companion.user
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

    suspend fun updateUser(
        id: Int,
        user: User,
        token: String,
        avatar: File,
        banner: File
    ): Int {
        val avatarRequestBody = avatar.asRequestBody("image/*".toMediaTypeOrNull())
        val bannerRequestBody = banner.asRequestBody("image/*".toMediaTypeOrNull())

        val avatarPart = if (avatar.name == "") null else MultipartBody.Part.createFormData("avatar", avatar.name, avatarRequestBody)
        val bannerPart = if (banner.name == "") null else MultipartBody.Part.createFormData("banner", banner.name, bannerRequestBody)

        return try {


            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .updateUser(
                    token = "Bearer $token",
                    id = id,
                    nom = user.nom.toRequestBody("text/plain".toMediaTypeOrNull()),
                    prenom = user.prenom.toRequestBody("text/plain".toMediaTypeOrNull()),
                    email = user.email.toRequestBody("text/plain".toMediaTypeOrNull()),
                    numtel = user.numtel.toRequestBody("text/plain".toMediaTypeOrNull()),
                    adressepostal = user.adressepostal.toRequestBody("text/plain".toMediaTypeOrNull()),
                    ville = user.ville.toRequestBody("text/plain".toMediaTypeOrNull()),
                    codepostal = user.codepostal.toRequestBody("text/plain".toMediaTypeOrNull()),
                    description = user.description.toRequestBody("text/plain".toMediaTypeOrNull()),
                    sexe = user.sexe.toRequestBody("text/plain".toMediaTypeOrNull()),
                    avatar = avatarPart,
                    banner = bannerPart
                )

            if (response.isSuccessful) {
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
           val response = RetrofitInstance
               .getRetrofitInstance()
               .create(ApiServices::class.java)
               .getUserByEmail(token= "Bearer $token", email = email)
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

           val response =  RetrofitInstance
               .getRetrofitInstance()
               .create(ApiServices::class.java)
               .getUserById(token= "Bearer $token", id = id)
           if (response.isSuccessful) {
                response.body()?.user1!!
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

    suspend fun addRib(rib: String, email : String, token: String): Int {
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java).
                addRib(token = "Bearer $token" , ribRequest = RibRequest(email = email, rib = rib))
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

    suspend fun updatePassword(token: String, oldPassword: String, newPassword: String):Int {
        val updateRequest = UpdatePasswordRequest(
            id = user.id.toString(),
            oldPassword = oldPassword,
            newPassword = newPassword
        )
         try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .updatePassword(token = "Bearer $token",
                    updatePasswordRequest = updateRequest)
            if (response.isSuccessful)
                 return 200
            if (response.code() == 400)
                return 400
            if (response.code() == 401)
                return 401
            if (response.code() == 404)
                return 404
             return 500
        }
        catch (e: Exception){
            return 500
        }

    }


    suspend fun forgotPasswordEmailVerify(email : String): Int {
        return try {
            val response =  RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .forgetPassword(email= email)
            if (response.isSuccessful) 200

            else 404
        } catch (e: Exception) { 500 }
    }

    suspend fun forgetPasswordDcVerify(email: String, code: String): Int {
        return try{
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .verifyCode(email= email, code = code)

            if (response.isSuccessful) {
                Log.d("response in data source", response.code().toString())
                200
            }
            else{
                Log.d("response in data source", response.code().toString())
                404
            }
        }
        catch (e: Exception){
            Log.d("response in data source error", e.message.toString())
            500

        }
    }

    suspend fun forgetPasswordUpdatePwd(email: String, password: String, code: String) : Int{
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .updatePasswordAfterCode(
                    ForgetPasswordRequest(
                        email = email,
                        newpassword = password,
                        digitcode = code
                    )
                )
            if (response.isSuccessful) 200
            else 400

        }
        catch (e: Exception) {
            500
        }
    }
}