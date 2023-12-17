package slikoo.kvrae.slikoo.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Feedback
import slikoo.kvrae.slikoo.data.datasources.entities.Invitation
import slikoo.kvrae.slikoo.data.datasources.entities.Reservation
import slikoo.kvrae.slikoo.data.datasources.remote.FeedbackRemoteDataSource
import slikoo.kvrae.slikoo.data.datasources.remote.InvitationsRemoteDataSource
import slikoo.kvrae.slikoo.data.datasources.remote.ReservationRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class FeedbackViewModel : ViewModel() {
    val reservationRDS: ReservationRemoteDataSource = ReservationRemoteDataSource()
    val invitationRDS: InvitationsRemoteDataSource = InvitationsRemoteDataSource()
    val feedbackRDS: FeedbackRemoteDataSource = FeedbackRemoteDataSource()

    var reservations = mutableStateListOf<Reservation>()
    val invitations = mutableStateListOf<Invitation>()
    val feedbacks = mutableStateListOf<Feedback>()

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)


    private fun getAllReservations() {
        viewModelScope.launch(Dispatchers.IO) {
            val filteredReservations: MutableList<Reservation>
            reservations.clear()
            try {
                isLoading = true
                filteredReservations = async {
                    reservationRDS.getMyReservations(
                        id = TempSession.user.id,
                        token = TempSession.token
                    )
                }.await()
                reservations.addAll(async {
                    filteredReservations.filter {
                        it.status == "Accepted"
                    }.toMutableList() }.await() )

            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
            } finally {
                isLoading = false
                Log.d ("Reservations for feedback", reservations.size.toString())
            }
        }
    }

    private fun getAllInvitations() {
        viewModelScope.launch(Dispatchers.IO) {
            val invitationsFiltered = mutableListOf<Invitation>()
            invitations.clear()
            try {
                isLoading = true
                async {
                    (invitationRDS.getInvitations(
                        invitations = invitationsFiltered,
                        id = TempSession.user.id,
                        token = TempSession.token
                    ))
                }.await()
                invitations.addAll(async {
                    invitationsFiltered.filter {
                        it.status == "Accepted"
                    }.toMutableList()
                }.await())
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
            } finally {
                isLoading = false
                Log.d ("Invitations for feedback", invitations.size.toString())
            }
        }
    }

    fun getFeedbacks(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                val result = async {
                    getAllInvitations()
                    getAllReservations()
                    getMySubmittedFeedbacks()
                }
                result.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
            } finally {
                isLoading = false
            }
        }
    }

    private fun getMySubmittedFeedbacks (){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                feedbacks.addAll(
                    async {
                        feedbackRDS.getMySubmittedFeedbacks(
                            id = TempSession.user.id,
                            token = TempSession.token
                        )
                    }.await()
                )
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
            } finally {
                isLoading = false
                Log.d ("feedbacks for feedback", feedbacks.size.toString())
            }
        }
    }

}