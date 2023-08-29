package slikoo.kvrae.slikoo.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.data.entities.User
import javax.inject.Singleton

@Singleton
class UserViewModel() : ViewModel() {
    val userRepository = UserRemoteDataSource()
    var user = mutableStateOf(User())
    var confirmPassword = mutableStateOf("")
    var token = mutableStateOf("")
    var isLogged = mutableStateOf(false)

    fun onValidateFirstPart(): List<String> {
        val emailRegex = Regex(pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")

        val validationMessages = mutableListOf<String>()
        if (user.value.firstName.isEmpty()) {  validationMessages.add("firstName is empty") }
        if (user.value.firstName.length < 3) { validationMessages.add("firstName is too short") }
        if (user.value.firstName.length > 20) { validationMessages.add("firstName is too long") }

        if (user.value.lastName.isEmpty()) { validationMessages.add("lastName is empty") }
        if (user.value.lastName.length < 3) { validationMessages.add("lastName is too short") }
        if (user.value.lastName.length > 20) { validationMessages.add("lastName is too long") }

        if (user.value.email.isEmpty()) { validationMessages.add("email is empty") }
        if (!emailRegex.matches(user.value.email)) { validationMessages.add("email is not valid") }

        if (user.value.password.isEmpty()) { validationMessages.add("password is empty") }
        if (confirmPassword.value.isEmpty()) { validationMessages.add("confirmPassword is empty") }
        if (user.value.password != confirmPassword.value) { validationMessages.add("password and confirmPassword are not equal") }

        return validationMessages
    }

    fun onValidateSecondPart(): List<String>{

        val phoneRegex = Regex(pattern = "[0-9]+")
        val validationMessages = mutableListOf<String>()

        if (user.value.phone.isEmpty()) { validationMessages.add("phone is empty") }
        if (user.value.phone.length < 8) { validationMessages.add("phone is too short") }
        if (user.value.phone.length > 20) { validationMessages.add("phone is too long") }
        if (!phoneRegex.matches(user.value.phone)) { validationMessages.add("phone is not valid") }

        if (user.value.postalCode.isEmpty()) { validationMessages.add("postal code is empty") }
        if (user.value.postalCode.length < 3) { validationMessages.add("postal code is too short") }
        if (user.value.postalCode.length > 20) { validationMessages.add("postal code is too long") }

        return validationMessages
    }

    fun onValidateThirdPart(): List<String>{
        val validationMessages = mutableListOf<String>()

        if (user.value.avatar.isEmpty()) { validationMessages.add("cid is empty") }
        return validationMessages
    }

    fun finalSignUpValidation(): List<String>{
        val validationMessages = mutableListOf<String>()

        if (user.value.avatar.isEmpty()) { validationMessages.add("avatar is empty")}
        validationMessages.addAll(onValidateFirstPart())
        validationMessages.addAll(onValidateSecondPart())
        validationMessages.addAll(onValidateThirdPart())
        return validationMessages

    }

    fun onLoginValidation(): List<String>{
        val emailRegex = Regex(pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val validationMessages = mutableListOf<String>()

        if (user.value.email.isEmpty()) { validationMessages.add("email is empty") }
        if (!emailRegex.matches(user.value.email)) { validationMessages.add("email is not valid") }

        if (user.value.password.isEmpty()) { validationMessages.add("password is empty") }

        return validationMessages
    }

    /*fun onLoginCheck(){
        preferencesDataStore(name = "Slikoo")
        viewModelScope.launch {
            val token = TokenDataStore(context = context).getUserToken()
            if (token.isNotEmpty()){
                val user = UserRepository().getUser(token)
                if (user != null){
                    UserState.isLogged = true
                    UserState.user = user
                }
            }
        }
    }*/

    fun onLogin () {9
        viewModelScope.launch {
            token.value = userRepository.login(user.value)
        }
    }



    override fun onCleared() {
        super.onCleared()
        println("UserViewModel destroyed!")
    }


}