package slikoo.kvrae.slikoo.viewmodels

import android.util.Log
import android.util.Patterns.EMAIL_ADDRESS
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource

class ForgetPasswordViewModel: ViewModel() {
    var email = mutableStateOf("karam.mannai@esprit.tn")
    val userRp = UserRemoteDataSource()
    var isEmailValid =  mutableStateOf(false)
    var resCode = mutableStateOf(0)

    fun onValidateEmailNotEmpty(): Boolean {
        return email.value.isNotEmpty()
    }
    fun onValidateEmail(): Boolean {
        return EMAIL_ADDRESS.matcher(email.value).matches()
    }

    fun onSendEmail() {
        viewModelScope.launch(Dispatchers.IO) {
            resCode.value = async { userRp.forgotPassword(email.value) }.await()
            Log.d("forget email", email.value)
            Log.d("forget resCode", resCode.value.toString())
        }
    }
    fun dialogTitle(): Int {
        return when (resCode.value) {
            200 -> slikoo.kvrae.slikoo.R.string.email_sent
            400 -> slikoo.kvrae.slikoo.R.string.email_not_found
            500 -> slikoo.kvrae.slikoo.R.string.email_not_sent
            else -> slikoo.kvrae.slikoo.R.string.error_message_email
        }
    }

    fun dialogMessage(): Int {
        return when (resCode.value) {
            200 -> slikoo.kvrae.slikoo.R.string.email_sent_message
            400 -> slikoo.kvrae.slikoo.R.string.email_not_found_message
            500 -> slikoo.kvrae.slikoo.R.string.email_not_sent_message
            else -> slikoo.kvrae.slikoo.R.string.error_message_email
        }
    }


}