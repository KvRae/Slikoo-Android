package slikoo.kvrae.slikoo.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.data.models.User
import javax.inject.Singleton

@Singleton
class UserViewModel() : ViewModel() {
    private var _user = mutableStateOf(User())
    var user : State<User> = _user



    fun onEmailChange(email: String) {
        _user.value = user.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _user.value = _user.value.copy(password = password)
    }

    fun onFirstNameChange(firstName: String) {
        _user.value = _user.value.copy(firstName = firstName)
    }
    override fun onCleared() {
        super.onCleared()
        println("UserViewModel destroyed!")
    }

    fun onSignUp() {

    }

    fun onLogin() {

    }

    fun onForgotPassword() {

    }

    fun onValidateEmail() {

    }

    fun onValidatePassword() {

    }

    fun onValidateFirstName() {

    }

    fun onValidateLastName() {

    }

    fun onValidatePhone() {

    }

    fun onValidateAddress() {

    }

    fun onValidatePostalCode() {

    }

    fun onValidateCity() {

    }

    fun onValidateCountry() {

    }

    fun onValidateState() {

    }

    fun onValidateGender() {

    }

    fun onValidateBirthDate() {

    }


}