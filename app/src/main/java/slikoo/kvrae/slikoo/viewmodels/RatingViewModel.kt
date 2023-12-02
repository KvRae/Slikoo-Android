package slikoo.kvrae.slikoo.viewmodels


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.FeedBack
import slikoo.kvrae.slikoo.data.datasources.remote.FeedbackRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class RatingViewModel : ViewModel() {
    val feedbackRDS = FeedbackRemoteDataSource()

    var feedBacks = mutableStateListOf<FeedBack>()
    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)

    fun getUserFeedbacks() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isError = false
                isLoading = true
                feedBacks.addAll(async { feedbackRDS.getFeedbacks(TempSession.token,TempSession.user.id) }.await())
            }
            catch (e: Exception) {
                e.printStackTrace()
                isError = true
            }
            finally {
                isLoading = false
            }
        }
    }
}