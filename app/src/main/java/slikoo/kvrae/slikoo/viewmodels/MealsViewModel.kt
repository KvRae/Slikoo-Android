package slikoo.kvrae.slikoo.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.remote.MealRemoteDataSource

class MealsViewModel(): ViewModel() {
    private val mealRemoteDataSource = MealRemoteDataSource()
    var meal = mutableStateOf(Meal())
    var meals = mutableListOf<Meal>()
    var isLoading = mutableStateOf(true)
    var searchText = mutableStateOf("")
    var filteredMeals = mutableListOf<Meal>()

    init {
        getAllMeals(meals)
    }

    fun filterMealsList(filter : String){
        filteredMeals.clear()
        if (filter.isEmpty()){
            filteredMeals.clear()
            filteredMeals.addAll(meals)
        }
        if (filter.isNotEmpty())
            for (meal in meals){
                if (meal.localisation.contains(filter,ignoreCase = true)
                    || meal.theme.contains(filter,ignoreCase = true)
                    || meal.genrenourriture.contains(filter,ignoreCase = true)
                    || meal.prix.contains(filter,ignoreCase = true)
                    || meal.nbr.contains(filter,ignoreCase = true)
                    || meal.description.contains(filter,ignoreCase = true)
                    || meal.date.contains(filter,ignoreCase = true)
                    || meal.heure.contains(filter,ignoreCase = true)
                )
                    filteredMeals.add(meal)
            }
    }

    private fun getAllMeals(meals : MutableList<Meal>)  {
        viewModelScope.launch {
            try {
                async { isLoading.value = true }.await()
                async {mealRemoteDataSource.getAllMeals(meals)  }.await()
                async { filteredMeals.addAll(meals) }.await()
                async { isLoading.value = false }.await()

            } catch (e: Exception) {
                Log.e("Meals Error", e.message.toString())
            }
        }
    }

    fun getMealById(id : Int)  {
        viewModelScope.launch(Dispatchers.IO) {
            if (id != 0) {
                try {
                    meal.value = async { mealRemoteDataSource.getMealById(id = id) }.await()
                } catch (e: Exception) {
                    Log.e("Meals Error", e.message.toString())
                }
            }
        }
    }

    fun dateConverter(date: String): String {
        val dateList = date.split("-")
        val day = dateList[2]
        var month = dateList[1]
        val year = dateList[0]
        when (month) {
            "01" -> month = "Janvier"
            "02" -> month = "Fevrier"
            "03" -> month = "Mars"
            "04" -> month = "Avril"
            "05" -> month = "Mai"
            "06" -> month = "Juin"
            "07" -> month = "Juillet"
            "08" -> month = "Aout"
            "09" -> month = "Septembre"
            "10" -> month = "Octobre"
            "11" -> month = "Novembre"
            "12" -> month = "Decembre"
        }
        return "$day $month $year"
    }
}

