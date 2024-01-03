package slikoo.kvrae.slikoo.data.datasources.remote

import android.annotation.SuppressLint
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.dto.UserDetailsRequest
import slikoo.kvrae.slikoo.data.datasources.entities.UserDetails
import slikoo.kvrae.slikoo.utils.TempSession

class UserDetailsRemoteDataSource {

    @SuppressLint("SuspiciousIndentation")
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

    suspend fun addUserDetails(token : String, userDetails: UserDetails): Int {
        return try{
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .addUserDetails(
                    token = "Bearer ${token}", user = UserDetailsRequest(
                        iduser = TempSession.user.id.toString(),
                        fumeur = userDetails.fumeur,
                        alcohol = userDetails.alcohol,
                        algalimentaire = userDetails.algalimentaire.joinToString(),
                        centreinteret = userDetails.centreinteret.joinToString (),
                        cherche = userDetails.cherche.joinToString(),
                        langues = userDetails.langues.joinToString(),
                        chercherplus = userDetails.chercherplus.joinToString(),
                        Facebooklink = userDetails.facebooklink,
                        InstagramLink = userDetails.InstagramLink,
                        TwitterLink = userDetails.TwitterLink,
                        LinkedinLink = userDetails.LinkedinLink
                    ))
                if (response.isSuccessful)
                    200
                else
                    400
        }
        catch (e: Exception){
            e.printStackTrace()
            500
        }
    }

    suspend fun updateUserDetails(token : String, userDetails: UserDetails): Int {
        return try{
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .updateUserDetails(
                    token = "Bearer $token", user = UserDetailsRequest(
                        iduser = userDetails.idusermain,
                        fumeur = userDetails.fumeur,
                        alcohol = userDetails.alcohol,
                        algalimentaire = userDetails.algalimentaire.joinToString(),
                        centreinteret = userDetails.centreinteret.joinToString (),
                        cherche = userDetails.cherche.joinToString(),
                        langues = userDetails.langues.joinToString(),
                        chercherplus = userDetails.chercherplus.joinToString(),
                        Facebooklink = userDetails.facebooklink,
                        InstagramLink = userDetails.InstagramLink,
                        TwitterLink = userDetails.TwitterLink,
                        LinkedinLink = userDetails.LinkedinLink
                    ))
                if (response.isSuccessful)
                    response.code()
                else
                    400
        }
        catch (e: Exception){
            e.printStackTrace()
            500
        }
    }
}