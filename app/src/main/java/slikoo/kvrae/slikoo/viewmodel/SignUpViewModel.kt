package slikoo.kvrae.slikoo.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import slikoo.kvrae.slikoo.data.datasources.entities.User

class SignUpViewModel: ViewModel() {

    var user = mutableStateOf(User())
    var confirmPassword = mutableStateOf("")




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

    fun onConfirmPassword(): Boolean{
        if (user.value.password == confirmPassword.value) return true
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
        return ""
    }

    fun onValidateDescription(): String {
        return ""
    }

    fun onValidateCid(): String {
        if (user.value.cinavatar.isEmpty()) return ""
        return ""
    }

    fun onValidateProfilePicture(): String{
        if(user.value.avatar.isEmpty()) return ""
        return ""
    }

    fun onRegister() {
        if (onValidateFirstName().isEmpty() && onValidateLastName().isEmpty()){
            viewModelScope
        }
        return

    }





}
