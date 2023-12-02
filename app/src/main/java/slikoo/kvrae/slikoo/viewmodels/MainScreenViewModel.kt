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

class MainScreenViewModel: ViewModel() {

    var user = mutableStateOf(User())
    private val userRDS = UserRemoteDataSource()
    var ribMessage = mutableStateOf("")

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var showDialog by  mutableStateOf(false)



    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                TempSession.user = async { userRDS.getUserByEmail(token = TempSession.token ,email = TempSession.email) }.await()
                user.value = async { TempSession.user }.await()
                async { if (user.value.RIB == null) user.value.RIB = "" }.await()
                isLoading = async { false }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading =  false
                isError = true
            }
            finally {
                isLoading =  false
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
                ribMessage.value ="Something went wrong"
                isLoading =  false
                isError =  true
            }
            finally {
                isLoading =  false
            }
        }
    }
}