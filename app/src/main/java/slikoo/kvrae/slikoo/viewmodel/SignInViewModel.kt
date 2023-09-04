package slikoo.kvrae.slikoo.viewmodel

import androidx.lifecycle.ViewModel
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource

class SignInViewModel: ViewModel() {
    val userRemoteDataSource = UserRemoteDataSource()


}