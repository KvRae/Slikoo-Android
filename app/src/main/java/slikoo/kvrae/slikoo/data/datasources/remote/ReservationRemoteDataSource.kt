package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.dto.ReservationRequest
import slikoo.kvrae.slikoo.data.datasources.entities.Reservation

class ReservationRemoteDataSource {


    suspend fun getMyReservations(id : Int, token: String): MutableList<Reservation> {
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .getReservations(
                    token = "Bearer $token",
                    ReservationRequest(idUser = id)
                )
            if (response.isSuccessful)
                response.body()?.reservations!!
            else {
                mutableListOf()
            }
        }
        catch (e: Exception) {
            Log.e("Reservations Error", e.message.toString())
            mutableListOf()
        }
    }

    suspend fun declineReservation(idMeal : Int,idUser: Int, token: String): Int {
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .declineReservation(
                    token = "Bearer $token",
                    idMeal = idMeal,
                    idUser = idUser
                )
            if (response.isSuccessful)
                200
            else {
                400
            }
        }
        catch (e: Exception) {
            Log.e("Reservations Error", e.message.toString())
            500
        }
    }
}