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
    val email = mutableStateOf("")
    val code = mutableStateOf("")
    val password =   mutableStateOf("")
    val confirmPassword =  mutableStateOf("")

    // Response code
    private val resCode = mutableStateOf(0)

    // Validation
    val isEmailValid =  mutableStateOf(false)
    val isCodeValid =  mutableStateOf(false)
    val isPasswordValid =  mutableStateOf(false)



    // Navigation and loading
    val onNavigateToCode= mutableStateOf(false)
    val onNavigateToPassword= mutableStateOf(false)
    val onNavigate = mutableStateOf(false)
    val isLoading = mutableStateOf(false)

    // Error messages
    val emailError by mutableStateOf("")
    val codeError by mutableStateOf("")
    var passwordError by mutableStateOf("")

    // Email methods
    private fun onEmailChange(email: String) {
        this.email.value = email
        isEmailValid.value = EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()
    }
    private fun onSendEmailToVerify(email : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                isLoading.value = true
                resCode.value = async { userRp.forgotPasswordEmailVerify(email) }.await()
                onNavigateToCode.value = async { resCode.value == 200 }.await()
            }
            catch (e: Exception){
                isEmailValid.value = false
            }
            finally {
                isLoading.value = false
            }
        }
    }
    fun onEmailVerify(){
        onEmailChange(this.email.value)
        if (isEmailValid.value)
            onSendEmailToVerify(email.value)
    }

    // OTP Code methods
    private fun onCodeChange(code: String) {
        this.code.value = code
        isCodeValid.value = code.isNotEmpty() && code.length == 6
    }
    private fun onSendCodeToVerify(email : String, code : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.value = true
                resCode.value = async { userRp.forgetPasswordDcVerify(email, code) }.await()
                onNavigateToPassword.value = async { resCode.value == 200 }.await()
            }
            catch (e: Exception){
                onNavigateToPassword.value = false
            }
            finally {
                isLoading.value = false
            }
        }
    }
    fun onCodeVerify(){
        onCodeChange(code.value)
        if (isCodeValid.value)
            onSendCodeToVerify(email.value, code.value)
    }

    // Password methods
    private fun onPasswordChange(password: String) {
        this.password.value = password
        isPasswordValid.value = password.isNotEmpty() && password == confirmPassword.value

    }
    private fun onSendPasswordToVerify(email : String, password : String, code : String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.value = true
                Log.d("code in view model", code)
                resCode.value = async { userRp.forgetPasswordUpdatePwd(email, password, code) }.await()
                onNavigate.value = async { resCode.value == 200 }.await()
            }
            catch (e: Exception){
                onNavigate.value = false
            }
            finally {
                isLoading.value = false
            }
        }
    }
    fun onPasswordVerify(){
        onPasswordChange(this.password.value)
        if (isPasswordValid.value)
            onSendPasswordToVerify(email.value, password.value, code.value)
    }
}


































