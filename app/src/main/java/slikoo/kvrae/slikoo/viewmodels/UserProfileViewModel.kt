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

class UserProfileViewModel: ViewModel() {

    val user = mutableStateOf(User())
    private val userRDS = UserRemoteDataSource()

    val isError = mutableStateOf(false)
    val isLoading = mutableStateOf(false)
    val navigate = mutableStateOf(false)

    fun getUserById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isError.value = false
                isLoading.value = true
                user.value = async {
                    userRDS.getUserById(token = TempSession.token,id = id)
                }.await()
            } catch (e: Exception) {
                isError.value = true
            }
            finally {
                isLoading.value =  false
            }
        }
    }

    fun deleteUser() {
        var resCode = 0
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isError.value = false
                isLoading.value = true
                resCode = async {
                    userRDS.deleteUser(token = TempSession.token,)
                }.await()
            } catch (e: Exception) {
                isError.value= true
            }
            finally {
                isLoading.value =  false
                when (resCode) {
                    200 -> {
                        navigate.value = true
                    }
                    else -> {
                        isError.value = true
                    }
                }
            }
        }
    }

}