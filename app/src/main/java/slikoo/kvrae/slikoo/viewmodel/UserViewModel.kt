package slikoo.kvrae.slikoo.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.data.entities.User
import javax.inject.Singleton

@Singleton
class UserViewModel() : ViewModel() {
    private val userRepository = UserRemoteDataSource()
    var user = mutableStateOf(User())
    var confirmPassword = mutableStateOf("")
    var token = mutableStateOf("")
    var isLogged = mutableStateOf(false)




    fun onLoginValidation(): List<String>{
        val emailRegex = Regex(pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val validationMessages = mutableListOf<String>()

        if (user.value.email.isEmpty()) { validationMessages.add("email is empty") }
        if (!emailRegex.matches(user.value.email)) { validationMessages.add("email is not valid") }

        if (user.value.password.isEmpty()) { validationMessages.add("password is empty") }

        return validationMessages
    }



    fun onLogin () {
        viewModelScope.launch {
            Dispatchers.IO.run { token.value = userRepository.login(user.value) }
        }
    }



    override fun onCleared() {
        super.onCleared()
        println("UserViewModel destroyed!")
    }


}