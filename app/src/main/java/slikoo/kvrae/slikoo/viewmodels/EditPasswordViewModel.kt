package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class EditPasswordViewModel : ViewModel() {
    val userRDS = UserRemoteDataSource()

    var oldPassword by mutableStateOf("")
    var newPassword by mutableStateOf("")
    var confirmPassword by mutableStateOf("")


    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var resCode by mutableStateOf(0)
    var showDialog by mutableStateOf(false)

    fun updatePassword(
        oldPassword: String,
        newPassword: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showDialog = false
                isLoading = true
                resCode = async {
                    userRDS.updatePassword(
                        token = TempSession.token,
                        oldPassword = oldPassword,
                        newPassword = newPassword,
                    )
                }.await()
                isLoading = async { false }.await()
            } catch (e: Exception) {
                isLoading = false
                isError = true
                resCode
            } finally {
                isLoading = false
                showDialog = true
            }
        }
    }
}