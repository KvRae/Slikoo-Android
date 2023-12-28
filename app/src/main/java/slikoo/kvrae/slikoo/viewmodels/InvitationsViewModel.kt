package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Invitation
import slikoo.kvrae.slikoo.data.datasources.remote.InvitationsRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class InvitationsViewModel : ViewModel() {
    private val invitationRDS = InvitationsRemoteDataSource()

    var invitations = mutableStateListOf<Invitation>()
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun getInvitations() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                val result = async {

                    invitationRDS.getInvitations(
                        invitations = invitations,
                        token = TempSession.token,
                        id = TempSession.user.id
                    )
                }
                result.await()
            } catch (e: Exception) {
                e.printStackTrace()
                invitations.addAll(emptyList())
                isError = true
            } finally {
                isLoading = false
                invitations.reverse()
            }
        }

    }

    fun acceptInvitation(
        idMeal: String, informationComp: String, idDemander: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                invitationRDS.acceptInvitation(
                    token = TempSession.token,
                    idMeal = idMeal,
                    idDemander = idDemander,
                    idOwner = TempSession.user.id.toString(),
                    informationComp = informationComp
                )
            } catch (e: Exception) {
                isError = true
            } finally {
                isLoading = false
            }
        }
    }

    fun declineInvitation(idMeal: Int, idDemander: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                invitationRDS.declineInvitation(
                    token = TempSession.token,
                    idUser = idDemander,
                    idMeal = idMeal
                )
            } catch (e: Exception) {
                isError = true
            } finally {
                isLoading = false
            }
        }
    }
}