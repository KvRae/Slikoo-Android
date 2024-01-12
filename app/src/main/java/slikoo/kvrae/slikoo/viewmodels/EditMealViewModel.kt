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
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.remote.MealRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession
import java.io.File

class EditMealViewModel: ViewModel() {
    private val mealRemoteDataSource = MealRemoteDataSource()
    val invitationTypes = listOf("Anniversaire", "Mariage", "Soirée", "Fête", "Réunion", "Autre")
    val themes = listOf("Cocktail", "Dîner", "Déjeuner", "Brunch", "Goûter", "Autre")
    val preferences = listOf("Halal", "Casher", "Végétarien", "Végétalien", "Autre")
    val genres = listOf("Famille", "Amis", "Collègues", "Entre fille", "Entre mecs", "Autre")


    private val mealRDS = MealRemoteDataSource()
    val meal = mutableStateOf(Meal())
    private var bannerUrl = (meal.value.avatarUrl.plus(meal.value.avatar)).toUri()
    val banner  = mutableStateOf(bannerUrl)
    private var resCode by mutableStateOf(0)

    var isLoading by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var isMealUpdated by mutableStateOf(false)



    fun getMealById(id : Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading = true
                meal.value = async { mealRDS.getMealById(id) }.await()
            }catch (e : Exception) {
                e.message
                isError = true
            }
            finally {
                isLoading = false
            }
        }
    }

    fun onUpdateMeal(mealBanner: File?, id: Int, meal: Meal) {
        resCode = 0
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isError = false
                isLoading = true
                resCode = async {
                    mealRemoteDataSource
                    .updateMeal(
                        token = TempSession.token,
                        meal = meal,
                        mealBanner = mealBanner ?: File(""),
                        id = id
                    ) }.await()
            } catch (e: Exception) {
                resCode = 500
            }

            finally {
                isLoading = false
            }
        }
    }
}
