package slikoo.kvrae.slikoo.data.datasources.remote

import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.UserDetails

class UserDetailsRemoteDataSource {

    suspend fun getUserDetails(token : String, id : Int): UserDetails {
        return try{
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .getUserDetailsId(
                    token = "Bearer ${token}", id = id)
                if (response.isSuccessful)
                    response.body()?.userDetails!!
                else
                    UserDetails()
        }
        catch (e: Exception){
            e.printStackTrace()
            UserDetails()
        }
    }

}