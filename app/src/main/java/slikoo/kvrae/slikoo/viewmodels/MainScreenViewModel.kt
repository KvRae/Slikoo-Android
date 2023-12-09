package slikoo.kvrae.slikoo.viewmodels

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class MainScreenViewModel: ViewModel() {

    var user by mutableStateOf(User())
    private val userRDS = UserRemoteDataSource()
    val userAvatar = (user.avatarUrl.plus(user.avatar)).toUri()

    var avatarUrl by mutableStateOf(userAvatar)
    var cinUrl by mutableStateOf((Uri.parse(user.cinavatarUrl+user.cinavatar)))
    var banner by mutableStateOf((Uri.parse(user.avatarbannerUrl+user.avatarbanner)))

    var ribMessage = mutableStateOf("")

    var oldPassword by mutableStateOf("")
    var newPassword by mutableStateOf("")
    var confirmPassword by mutableStateOf("")

    var resCode by mutableStateOf(0)


    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var showDialog by  mutableStateOf(false)



    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                TempSession.user = async { userRDS.getUserByEmail(token = TempSession.token ,email = TempSession.email) }.await()
                user = async { TempSession.user }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
            }
            finally {
                isLoading =  false
            }
        }
    }

    fun updatePassword(oldPassword: String, newPassword: String, confirmPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading =  true
                resCode = async { userRDS.updatePassword(oldPassword = oldPassword, token = TempSession.token, newPassword = newPassword ) }.await()
            } catch (e: Exception) {
                isError =  true
            }
            finally {
                isLoading =  false
            }
        }
    }
}