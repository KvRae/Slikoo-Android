package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class MainScreenViewModel(): ViewModel() {

    val user = mutableStateOf(User())
    private val userRDS = UserRemoteDataSource()
    var ribMessage = mutableStateOf("")
    var isLoading = false
    var isError = false


    init {
        isLoading = true
        isError = false
        getUser()
    }


    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = async { true }.await()
                TempSession.user = async { userRDS.getUserByEmail(token = TempSession.token ,email = TempSession.email) }.await()
                user.value = async { TempSession.user }.await()
                isLoading = async { false }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading = async { false }.await()
                isError = async { true }.await()
            }
        }
    }

    fun addRib() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = async { true }.await()
                ribMessage.value = async { userRDS.addRib(user = user.value, token = TempSession.token).toString() }.await()
                user.value = async { TempSession.user }.await()
                isLoading = async { false }.await()
            } catch (e: Exception) {
                ribMessage.value = async { "Something went wrong" }.await()
                isLoading = async { false }.await()
                isError = async { true }.await()
            }
        }
    }
}