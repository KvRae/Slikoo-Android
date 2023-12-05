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
    var userDetails by mutableStateOf(UserDetails())
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun getUserDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                userDetails = async { userDetailsRDS.getUserDetails(
                    TempSession.token,
                    TempSession.user?.id!!
                ) }.await()
            } catch (e: Exception) {
                e.printStackTrace()

            }
            finally {
                isLoading = false
            }
        }
    }
}