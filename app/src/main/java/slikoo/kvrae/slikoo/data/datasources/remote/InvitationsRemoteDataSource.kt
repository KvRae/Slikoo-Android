package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.dto.InvitationRequest
import slikoo.kvrae.slikoo.data.datasources.entities.Invitation

class InvitationsRemoteDataSource {

    suspend fun getInvitations(invitations: MutableList<Invitation>, token: String, id: Int) {
        try {
            invitations.clear()
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .getInvitations(token = "Bearer $token", id = id)
            if (response.isSuccessful) {
                invitations.addAll(response.body() ?: emptyList())
            } else
                Log.d("Invitations Error", response.message())
            invitations.addAll(emptyList())
        } catch (e: Exception) {
            e.printStackTrace()
            invitations.addAll(emptyList())
            Log.e("Invitations Error", e.message.toString())
        }
    }

    suspend fun acceptInvitation (
        token: String,
        idDemander: String,
        idOwner: String,
        idMeal: String,
        informationComp: String) : Int
    {
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .acceptInvitation(
                   token = "Bearer $token",
                    body = InvitationRequest(
                        idDemander = idDemander,
                        idOwner = idOwner,
                        idMeal = idMeal,
                        informationComp = informationComp
                    )
                )
            if (response.isSuccessful) {
                200
            } else
                400
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Invitations Error", e.message.toString())
            500
        }
    }

    suspend fun declineInvitation(token: String, idUser: Int, idMeal: Int): Int {
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .declineInvitation(
                    token = "Bearer $token",
                    idUser = idUser,
                    idMeal = idMeal
                )
            if (response.isSuccessful)
                200
            else
                400

        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Invitations Error", e.message.toString())
            500
        }
    }
}