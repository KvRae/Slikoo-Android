package slikoo.kvrae.slikoo.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class SignInViewModel(): ViewModel() {
    private val userRDS = UserRemoteDataSource()
    var user = mutableStateOf(User(email = "hamzabenmahmoud9898@gmail.com", password = "12345678"))
    var token = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var isError = mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    var navigate = mutableStateOf(false)




    fun onLoginValidation(): List<String>{
        val emailRegex = Regex(pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val validationMessages = mutableListOf<String>()
        if (user.value.email.isEmpty()) { validationMessages.add("email is empty") }
        if (!emailRegex.matches(user.value.email)) { validationMessages.add("email is not valid") }
        if (user.value.password.isEmpty()) { validationMessages.add("password is empty") }
        return validationMessages
    }

    fun onLogin () {
        viewModelScope.launch(Dispatchers.IO) {
            async { isLoading.value = true }
            try {
                token.value = async { userRDS.authUser(user.value) }.await()
                Log .d("Login token", token.value)
                isError.value = async { token.value.length <= 1 }.await()
                Log.d ("Login isError", isError.value.toString())
                async { if (token.value.length == 1) { errorMessage.value = "Bad credentials"  }}.await()
                async { if (token.value.isEmpty()) { errorMessage.value =  "Something went wrong" } }.await()
                Log.d ("Login errorMessage", errorMessage.value)
                async {  if (!isError.value) { TempSession.token = token.value; TempSession.email =  user.value.email }}.await()
                async { if (TempSession.token.isNotEmpty() && token.value.length>1) navigate.value = true }.await()
                async { isLoading.value = false }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                async { isLoading.value = false }.await()
                async { isError.value = true }.await()
            }
        }
    }
}