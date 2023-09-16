package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession
import java.io.File

class SignInViewModel(): ViewModel() {
    private val userRDS = UserRemoteDataSource()
    var user = mutableStateOf(User(email = "hamzabenmahmoud9898@gmail.com", password = "12345678"))
    var token = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var navigate = mutableStateOf(false)

    val avatar: File = File("C:\\Users\\karam\\OnDrive\\Desktop\\avatar.jpg")
    private val cinAvatar: File = File("C:\\Users\\karam\\OnDrive\\Desktop\\cin.jpg")


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
            token.value = async { userRDS.authUser(user.value) }.await()
            TempSession.token = async { token.value}.await()
            TempSession.email = async { user.value.email}.await()
            user.value = async { userRDS.getUserByEmail(token.value, user.value.email) }.await()
            user.value.password = async { "****************" }.await()
            async { isLoading.value = false }
//            async { session.setUserToken(token.value) }.await()
//            async { session.setUserEmail(user.value.email) }.await()
//            async { db.userDao().insertUser(user = user.value) }


            async { if (TempSession.token.isNotEmpty()) navigate.value = true }.await()


        }

    }





}