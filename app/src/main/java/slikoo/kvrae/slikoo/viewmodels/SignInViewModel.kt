
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

    var user by mutableStateOf(User(
        email = "hamzabenmahmoud9898@gmail.com",
        password = "12345678")
    )

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
            try {
                isLoading = true
                token = async { userRDS.authUser(user) }.await()
                isError = async { token.length <= 1 }.await()
                if (token.length == 1) errorMessage = async {   "Bad credentials"  }.await()
                if (token.isEmpty()) errorMessage =  async { "Something went wrong" }.await()
                if (!isError) async { TempSession.token = token; TempSession.email =  user.email;navigate = true }.await()
            } catch (e: Exception) {
                e.printStackTrace()
                isError = true
            } finally {
                isLoading = false
            }
        }
    }
}
