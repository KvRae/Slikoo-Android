package slikoo.kvrae.slikoo.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import java.io.File

class SignInViewModel: ViewModel() {
    private val userRepository = UserRemoteDataSource()
    var user = mutableStateOf(User(
        postalCode = "12345",
        address = "address",
        city = "city",
        country = "country",
        firstName = "firstName",
        lastName = "lastName",
        phone = "12345678",
        about = "Hello babes",
        email = "hamzabenmahmoud9878@gmail.com",
        password = "12345678"))
    var confirmPassword = mutableStateOf("")
    var token = mutableStateOf("")
    var isLogged = mutableStateOf(false)

    val avatar: File = File("C:\\Users\\karam\\OnDrive\\Desktop\\avatar.jpg")
    val cinAvatar: File = File("C:\\Users\\karam\\OnDrive\\Desktop\\cin.jpg")


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
            token.value = async { userRepository.authUser(user.value) }.await()
            if (token.value.isNotEmpty()){
                user.value = async { userRepository.getUserByEmail(token.value, user.value.email) }.await()
                async { onRegister() }.await()

            }

        }
    }

    fun onRegister() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.createUser(user.value, avatar, cinAvatar)
        }
    }




}