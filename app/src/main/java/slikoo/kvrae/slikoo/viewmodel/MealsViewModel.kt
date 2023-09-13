package slikoo.kvrae.slikoo.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.remote.MealRemoteDataSource

class MealsViewModel(): ViewModel() {
    private val mealRemoteDataSource = MealRemoteDataSource()
    var meal = mutableStateOf(Meal())
    var meals = mutableListOf<Meal>()


    private val _searchText = mutableStateOf("")
    private val _isSearching = mutableStateOf(false)

    init {
        getAllMeals(meals)
    }


     private fun getAllMeals(meals : MutableList<Meal>)  {
        viewModelScope.launch {
            try {
                mealRemoteDataSource.getAllMeals(meals)
            } catch (e: Exception) {
                Log.e("Meals Error", e.message.toString())
            }
        }
    }
}