package slikoo.kvrae.slikoo.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import java.io.File

class SignUpViewModel: ViewModel() {

    private val userRepository = UserRemoteDataSource()
    var cid by  mutableStateOf(
        File("")
    )
    var profilePicture by mutableStateOf(
        File("")
    )


    var user = mutableStateOf(User(
        nom = "Karam",
        prenom = "MANNAI",
        email = "karam.mannaiii@esprit.tn",
        password = "123456",
        numtel = "12345678",
        codepostal = "1234",
        avatar = profilePicture.name,
        cinavatar = cid.name
    ))

    var confirmPassword = mutableStateOf("123456")

    fun onValidateFirstName(): String {
        if (user.value.nom.isEmpty()) return "remplir ce champs"
        if (user.value.nom.length < 2) return "nom est court"
        return ""
    }

    fun onValidateLastName(): String{
        if (user.value.prenom.isEmpty()) return "Ce champ est obligatoire"
        if (user.value.prenom.length < 2) return "votre prenom est tout court"
        return ""
    }

    fun onValidateEmail(): String {
        val emailRegex = Regex(pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        if (!emailRegex.matches(user.value.email)) return "email Invalid"
        if (user.value.email.isEmpty()) return "Remplir ce champs"
        if (user.value.email.length < 3) return "Remplir ce champs"
        return ""
    }

    fun onValidatePassword(): String {
        if (user.value.password.isEmpty()) return "champ obligatoir"
        if (user.value.password.length < 4) return "votre mot de passe est trop court"
        return ""
    }

    fun onConfirmPassword(): String{
        if (user.value.password == confirmPassword.value && user.value.password.isNotEmpty()) return ""
        return "mot de passe incorrect"
    }

    fun onValidateFirstForm(): Boolean{
        if (onValidateFirstName().isEmpty()
            && onValidateLastName().isEmpty()
            && onValidateEmail().isEmpty()
            && onValidatePassword().isEmpty()
            && onConfirmPassword().isEmpty()) return true
        return false
    }

    fun onValidatePhone(): String {
        val phoneRegex = Regex(pattern = "[0-9]+")
        if (user.value.numtel.isEmpty())  return "Phone is empty"
        if (user.value.numtel.length < 8)  return "phone is too short"
        if (user.value.numtel.length > 20)  return "phone is too long"
        if (!phoneRegex.matches(user.value.numtel)) return "phone is incorrect "
        return ""
    }

    fun onValidatePostalCode(): String{
        if (user.value.codepostal.isEmpty()) return "postal code is empty"
        if (user.value.codepostal.length < 4) return "postal code is too short"
        if (user.value.codepostal.length > 10) return "postal code is too long"
        return ""
    }

//    fun onValidateDescription(): String {
//        return ""
//    }

    fun onValidateSecondPart(): Boolean{
        if (onValidatePhone().isEmpty() && onValidatePostalCode().isEmpty()) return true
        return false
    }

    fun onValidateCid(): String {
        if (user.value.cinavatar.isEmpty()) return ""
        return ""
    }

    fun onValidateProfilePicture(): String{
        if(user.value.avatar.isEmpty()) return ""
        return ""
    }

    fun onRegister(user : User = this.user.value, profilePicture: File = this.profilePicture, cid: File = this.cid) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                userRepository.register(user, profilePicture, cid)
                Log.d("Retro register", "REGISTER ok")
            } catch (e: Exception) {
                Log.d("Retro register", e.message.toString())
            }

        }
    }

    fun onImageSelected(cid: Uri?) {
        if (cid != null) {
            user.value = user.value.copy(cinavatar = cid.toString())
        }
    }


}
