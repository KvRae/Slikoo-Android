package slikoo.kvrae.slikoo.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.remote.MealRemoteDataSource

class EditMealViewModel: ViewModel() {



    val invitationTypes = listOf("Anniversaire", "Mariage", "Soirée", "Fête", "Réunion", "Autre")
    val themes = listOf("Cocktail", "Dîner", "Déjeuner", "Brunch", "Goûter", "Autre")
    val preferences = listOf("Halal", "Casher", "Végétarien", "Végétalien", "Autre")
    val genres = listOf("Famille", "Amis", "Collègues", "Entre fille", "Entre mecs", "Autre")

    private val mealRDS = MealRemoteDataSource()
    var meal by mutableStateOf(Meal())

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var isMealUpdated by mutableStateOf(false)



    fun getMealById(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                meal = async { mealRDS.getMealById(id) }.await()


            }catch (e : Exception) {
                e.message
            }
            finally {
                isLoading = false
            }
        }
    }
}