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
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.FeedbackRemoteDataSource
import slikoo.kvrae.slikoo.data.datasources.remote.InvitationsRemoteDataSource
import slikoo.kvrae.slikoo.data.datasources.remote.ReservationRemoteDataSource
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class FeedbackViewModel : ViewModel() {

    private val reservationRDS: ReservationRemoteDataSource = ReservationRemoteDataSource()
    private val invitationRDS: InvitationsRemoteDataSource = InvitationsRemoteDataSource()
    private val feedbackRDS: FeedbackRemoteDataSource = FeedbackRemoteDataSource()
    private val userRDS: UserRemoteDataSource = UserRemoteDataSource()

    var reservations = mutableStateListOf<Reservation>()
    val invitations = mutableStateListOf<Invitation>()

    val feedbacks = mutableStateListOf<Feedback>()


    var resCode by mutableStateOf(0)
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)


    private fun getAllReservations() {
        viewModelScope.launch(Dispatchers.IO) {
            val filteredReservations: MutableList<Reservation>

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
                    invitations.clear()
                    getAllInvitations()
                    reservations.clear()
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

    fun addFeedback(
        idReciver : Int,
        idMeal : Int,
        comment : String,
        rate : Int
    ){
     viewModelScope.launch(Dispatchers.IO) {
         try {
             isLoading = true
              resCode = async {
                 feedbackRDS.submitFeedback(
                     feedback = Feedback(
                            id = 0,
                            rate = rate,
                            comment = comment,
                            date = "",
                            provider = TempSession.user,
                            recipient = User(id = idReciver),
                            imageProvider = listOf()
                     ),
                     idMeal = idMeal,
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

    fun getMyFeedbackByUserMeal(idUser: Int, idMeal: Int): Feedback {
        var feedback = Feedback()
        try {
            isLoading = true
            feedbacks.forEach {
                if (it.recipientId == idUser.toString() && it.idMeal == idMeal.toString())
                    feedback = it

            }
        } catch (e: Exception) {
            e.printStackTrace()
            isError = true
        } finally {
            isLoading = false
        }
        return feedback

    }
    fun verifyFeedbackSubmitted(idUser: Int, idMeal: Int): Boolean {
        try {
            feedbacks.forEach {
                if (it.recipientId == idUser.toString() && it.idMeal == idMeal.toString())
                    return true
            }
        } catch (e: Exception) {
            return false
        }
        return false
    }





}