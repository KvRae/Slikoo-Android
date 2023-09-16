package slikoo.kvrae.slikoo.viewmodels

import android.net.Uri
import androidx.lifecycle.ViewModel
import javax.inject.Singleton

@Singleton
class UserViewModel() : ViewModel() {




    override fun onCleared() {
        super.onCleared()
        println("UserViewModel destroyed!")
    }

    fun onImageSelected(it: Uri?) {
        TODO("Not yet implemented")
    }


}