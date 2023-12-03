package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Invitation
import slikoo.kvrae.slikoo.data.datasources.remote.InvitationsRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class InvitationsViewModel: ViewModel() {
    val invitationRDS = InvitationsRemoteDataSource()

    var invitations = mutableListOf<Invitation>()
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun getInvitations() {
        invitations.clear()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                invitationRDS.getInvitations(
                    invitations = invitations,
                    token = TempSession.token,
                    id = TempSession.user.id
                    )
            } catch (e: Exception) {
                e.printStackTrace()
                invitations.addAll(emptyList())
                isError = true
            } finally {
                isLoading = false
            }
        }

    }
}