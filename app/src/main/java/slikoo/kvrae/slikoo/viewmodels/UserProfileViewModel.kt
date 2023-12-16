package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class UserProfileViewModel: ViewModel() {

    var user by mutableStateOf(User())
    private val userRDS = UserRemoteDataSource()
    var isError by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    fun getUserById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isError = false
                isLoading = true
                user = async {
                    userRDS.getUserById(token = TempSession.token,id = id)
                }.await()
            } catch (e: Exception) {
                isError = true
            }
            finally {
                isLoading =  false
            }
        }
    }

}