package slikoo.kvrae.slikoo.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.util.PatternsCompat.EMAIL_ADDRESS
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource

class ForgetPasswordViewModel: ViewModel() {
    private val userRp = UserRemoteDataSource()

    // Fields
    var email by mutableStateOf("")
    var code by mutableStateOf("")
    var password by   mutableStateOf("")
    var confirmPassword by  mutableStateOf("")

    // Response code
    var resCode by mutableStateOf(0)

    // Validation
    var isEmailValid by  mutableStateOf(false)
    var isCodeValid by  mutableStateOf(false)
    var isPasswordValid by  mutableStateOf(false)



    // Navigation and loading
    var onNavigate by mutableStateOf(false)
    var isLoading by mutableStateOf(false)

    // Error messages
    var emailError by mutableStateOf("")
    var codeError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    // Email methods
    fun onEmailChange(email: String) {
        this.email = email
        isEmailValid = EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }
    private fun onSendEmailToVerify(email : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                resCode = async { userRp.forgotPasswordEmailVerify(email) }.await()
                onNavigate = async { resCode == 200 }.await()
                emailError = onEmailErrorChange(resCode)
            }
            catch (e: Exception){
                isEmailValid = false
                emailError = onEmailErrorChange(resCode)

            }
            finally {
                isLoading = false
            }
        }
    }
    fun onEmailVerify(){
        onEmailChange(this.email)
        if (isEmailValid)
            onSendEmailToVerify(email)
    }
    fun onEmailErrorChange(resCode : Int) : String{
        return when (resCode) {
            200 -> "code envoyé par email"
            404 -> "email introuvable"
            500 -> " erreur serveur"
            else -> " erreur serveur"
        }
    }

    // OTP Code methods
    fun onCodeChange(code: String) {
        this.code = code
        isCodeValid = code.isNotEmpty() && code.length == 6
    }
    private fun onSendCodeToVerify(email : String, code : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                resCode = async { userRp.forgetPasswordDcVerify(email, code) }.await()
                onNavigate = async { resCode == 200 }.await()
            }
            catch (e: Exception){
                onNavigate = false
            }
            finally {
                isLoading = false
            }
        }
    }
    fun onCodeVerify(){
        onCodeChange(code)
        if (isCodeValid)
            onSendCodeToVerify(email, code)
    }

    // Password methods
    fun onPasswordChange(password: String) {
        this.password = password
        isPasswordValid = password.isNotEmpty() && password == confirmPassword

    }
    private fun onSendPasswordToVerify(email : String, password : String, code : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                Log.d("code in view model", code)
                resCode = async { userRp.forgetPasswordUpdatePwd(email, password, code) }.await()
                onNavigate = async { resCode == 200 }.await()
            }
            catch (e: Exception){
                onNavigate = false
            }
            finally {
                isLoading = false
            }
        }
    }
    fun onPasswordVerify(){
        onPasswordChange(this.password)
        if (isPasswordValid)
            onSendPasswordToVerify(email, password, code)
    }
}


































