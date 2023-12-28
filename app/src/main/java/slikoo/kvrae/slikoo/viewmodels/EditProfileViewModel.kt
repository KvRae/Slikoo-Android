package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession
import java.io.File

class EditProfileViewModel : ViewModel() {
    private val userRDS = UserRemoteDataSource()

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var showDialog by mutableStateOf(false)
    var resCode by mutableStateOf(0)
    var navigate by mutableStateOf(false)

    var user by mutableStateOf(TempSession.user)

    val avatarUrl = (user.avatarUrl.plus(user.avatar)).toUri()
    val bannerUrl = (user.avatarbannerUrl.plus(user.avatarbanner)).toUri()
    val cinUrl = (user.cinavatarUrl.plus(user.cinavatar)).toUri()

    var userAvatar by mutableStateOf(avatarUrl)
    var userBanner by mutableStateOf(bannerUrl)
    var userCin by mutableStateOf(cinUrl)

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = async { true }.await()
                user = async {
                    userRDS.getUserByEmail(
                        token = TempSession.token,
                        email = user.email
                    )
                }.await()
                isLoading = async { false }.await()
                user
            } catch (e: Exception) {
                isLoading = false
                isError = true
                user
            } finally {
                isLoading = false
            }
        }
    }

    fun addRib(rib: String ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = async { true }.await()
                resCode = async { userRDS.addRib(
                    rib = rib,
                    email=TempSession.email,
                    token = TempSession.token
                ) }.await()
                isLoading = async { false }.await()
            } catch (e: Exception) {
                resCode = 500
                isLoading = false
                isError = true
            } finally {
                isLoading = false
            }
        }
    }

    fun verifyRib(rib: String) : Boolean {
        val ribRegex = Regex("^FR/d{2}/s?/d{5}/s?/d{5}/s?/d{11}/s?/d{2}$")
        return ribRegex.matches(rib)

    }

    fun updateUser(user: User, userAvatar: File?, userBanner: File?, cin: File?) {
        user.nom.ifEmpty { user.nom = TempSession.user.nom }
        user.prenom.ifEmpty { user.prenom = TempSession.user.prenom }
        user.numtel.ifEmpty { user.numtel = TempSession.user.numtel }
        user.codepostal.ifEmpty { user.codepostal = TempSession.user.codepostal }
        user.ville.ifEmpty { user.ville = TempSession.user.ville }
        user.adressepostal.ifEmpty { user.adressepostal = TempSession.user.adressepostal }
        user.sexe.ifEmpty { user.sexe = TempSession.user.sexe }
        user.ville.ifEmpty { user.ville = TempSession.user.ville }
        user.description.ifEmpty { user.description = TempSession.user.description }

        viewModelScope.launch(Dispatchers.IO) {
            try {
                navigate = false
                isLoading = true
                resCode = async {
                    userRDS.updateUser(
                        id = TempSession.user.id,
                        user = user,
                        token = TempSession.token,
                        avatar = userAvatar?: File(""),
                        banner = userBanner ?: File(""),
                    )
                }.await()
                navigate = async { resCode == 200 }.await()
                isLoading = async { false }.await()
            } catch (e: Exception) {
                resCode = 500
                isLoading = false
                isError = true
            } finally {
                isLoading = false
            }
        }
    }

    fun userToViewModel(userParam: User) {
    }



}