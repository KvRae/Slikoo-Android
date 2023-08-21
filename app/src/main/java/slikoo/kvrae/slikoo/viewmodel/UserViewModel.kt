package slikoo.kvrae.slikoo.viewmodel

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.data.repository.UserRepository

class UserViewModel(private  val userRepository: UserRepository) : ViewModel() {
    init {
        println("UserViewModel created!")
    }



    override fun onCleared() {
        super.onCleared()
        println("UserViewModel destroyed!")
    }

    fun onSignUp(){

    }

    fun onLogin(){

    }

    fun onForgotPassword(){

    }

    fun onValidateEmail(){

    }

    fun onValidatePassword(){

    }



}