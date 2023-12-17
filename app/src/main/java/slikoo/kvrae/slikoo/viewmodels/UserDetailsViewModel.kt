package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.UserDetails
import slikoo.kvrae.slikoo.data.datasources.remote.UserDetailsRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class UserDetailsViewModel: ViewModel() {
    val userDetailsRDS = UserDetailsRemoteDataSource()
    var userDetails by mutableStateOf(UserDetails(
        iduser = TempSession.user.id.toString(),
    ))
    val choices = listOf("oui","non")
    val langues = listOf(
        "Le mandarin",
        "Espagonl",
        "Francais",
        "Anglais",

        )
    val intrests = listOf<String>()

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var navigate by mutableStateOf(false)
    private var resCode by mutableStateOf(0)

    fun getUserDetails(id : Int = TempSession.user.id) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                userDetails = async { userDetailsRDS.getUserDetails(
                    TempSession.token,
                    id

                ) }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true

            }
            finally {
                isLoading = false
            }
        }
    }

    fun addUserDetails(userDetails: UserDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                navigate = false
                isLoading = true
                resCode = async {
                    userDetailsRDS.addUserDetails(
                    TempSession.token,
                    userDetails

                ) }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
                resCode = 500

            }
            finally {
                isLoading = false
                navigate = resCode == 200
            }
        }
    }

    fun updateUserDetails(userDetails: UserDetails) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                navigate = false
                isLoading = true
                resCode = async {
                    userDetailsRDS.updateUserDetails(
                    TempSession.token,
                    userDetails

                ) }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
                resCode = 500

            }
            finally {
                isLoading = false
                navigate = resCode == 200
            }
        }
    }
}