package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Reservation
import slikoo.kvrae.slikoo.data.datasources.remote.ReservationRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class ReservationViewModel : ViewModel() {
    val reservationRDS = ReservationRemoteDataSource()
    var reservations = mutableListOf<Reservation>()

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun getAllReservations() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                reservations = async {
                    reservationRDS.getMyReservations(
                        id = TempSession.user.id,
                        token = TempSession.token
                    )
                }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
            } finally {
                isLoading = false
            }
        }
    }

    fun declineReservation(idMeal : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                val response = async {
                    reservationRDS.declineReservation(
                        idMeal = idMeal,
                        idUser = TempSession.user.id,
                        token = TempSession.token
                    )
                }.await()
                if (response == 200) {
                    reservations.removeIf { (it.meal?.id ?: 0) == idMeal }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
            } finally {
                isLoading = false
            }
        }
    }


}