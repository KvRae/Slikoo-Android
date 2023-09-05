package slikoo.kvrae.slikoo.viewmodel

import androidx.lifecycle.ViewModel
import javax.inject.Singleton

@Singleton
class UserViewModel() : ViewModel() {




    override fun onCleared() {
        super.onCleared()
        println("UserViewModel destroyed!")
    }


}