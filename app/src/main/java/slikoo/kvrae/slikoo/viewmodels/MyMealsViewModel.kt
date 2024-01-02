package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.remote.MealRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession

class MyMealsViewModel: ViewModel() {

    private val mealsRepository = MealRemoteDataSource ()
    val myMeals = mutableStateListOf<Meal>()
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf(false)
    private val isDeleted = mutableStateOf(false)



    fun getMyMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.value = true
                error.value = false
                mealsRepository.getMyMeals(
                    id = TempSession.user.id,
                    token = TempSession.token,
                    meals = myMeals,

                    )
            } catch (e: Exception) {
                error.value = true
            } finally {
                isLoading.value = false
            }
        }
    }

    fun deleteMeal(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.value = true
                error.value = false
                isDeleted.value = mealsRepository.deleteMeal(id = id, token = TempSession.token,) == 200
            } catch (e: Exception) {
                error.value = true
            } finally {
                isLoading.value = false
                if (isDeleted.value) myMeals.removeIf { it.id == id }
            }
        }
    }

}