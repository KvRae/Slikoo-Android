import android.util.Log
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

class SignInViewModel : ViewModel() {
    private val userRDS = UserRemoteDataSource()

    var user by mutableStateOf(User(email = "hamzabenmahmoud9898@gmail.com", password = "12345678"))
    var token by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var errorMessage by mutableStateOf("")
    var navigate by mutableStateOf(false)

    fun onLoginValidation(): List<String> {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val validationMessages = mutableListOf<String>().apply {
            if (user.email.isEmpty()) add("Email is empty")
            if (!emailRegex.matches(user.email)) add("Email is not valid")
            if (user.password.isEmpty()) add("Password is empty")
        }
        return validationMessages
    }

    fun onLogin () {
        viewModelScope.launch(Dispatchers.IO) {
            async { isLoading = true }
            try {
                token = async { userRDS.authUser(user) }.await()
                Log .d("Login token", token)
                isError = async { token.length <= 1 }.await()
                Log.d ("Login isError", isError.toString())
                async { if (token.length == 1) { errorMessage = "Bad credentials"  }}.await()
                async { if (token.isEmpty()) { errorMessage =  "Something went wrong" } }.await()
                Log.d ("Login errorMessage", errorMessage)
                async {  if (!isError) { TempSession.token = token; TempSession.email =  user.email }}.await()
                async { if (TempSession.token.isNotEmpty() && token.length>1) navigate = true }.await()
                async { isLoading = false }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                async { isLoading = false }.await()
                async { isError = true }.await()
            }
            finally {
                async { isLoading = false }.await()
                async { navigate = true }.await()
            }
        }
    }
}
