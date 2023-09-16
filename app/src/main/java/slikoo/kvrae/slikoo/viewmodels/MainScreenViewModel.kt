package slikoo.kvrae.slikoo.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.SessionDataStore
import slikoo.kvrae.slikoo.utils.TempSession

class MainScreenViewModel( private val session: SessionDataStore): ViewModel() {

    val user = mutableStateOf(User())
    private val userRemoteDataSource = UserRemoteDataSource()

    init {
        getUser()
    }


    private fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            user.value = userRemoteDataSource.getUserByEmail(
                token = TempSession.token,
                email = session.getUserEmail()
            )
        }
        Log.wtf("MainScreenViewModel", "getUser: ${user.value}")
        Log.wtf("MainScreenViewModel", "getUser: ${user.value.email}")

    }
}