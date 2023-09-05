package slikoo.kvrae.slikoo.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.remote.MealRemoteDataSource
import slikoo.kvrae.slikoo.data.datasources.entities.Meal

class MealsViewModel(): ViewModel() {
    private val mealRemoteDataSource = MealRemoteDataSource()
    var meals = mutableListOf<Meal>()

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