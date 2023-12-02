package slikoo.kvrae.slikoo.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.MealRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession
import java.io.File

class MealsViewModel(): ViewModel() {
    private val mealRemoteDataSource = MealRemoteDataSource()
    // Objects
    val user = mutableStateOf(User())
    val meal = mutableStateOf(Meal(
        localisation = "PSG",
        type = " la pute",
        genre = " la poeme",
        genrenourriture = " la poeme",
        description = "Gourmandise et convivialité sont les maîtres mots de ce repas. Venez découvrir les saveurs de la cuisine marocaine et partager un moment de partage et de convivialité.",
        prix = "20",
        nbr = "4",
        date = "01/01/2021",
        heure = "14:00",
        iduser = "91",
    ))
    // Lists
    val myMeals = mutableStateListOf<Meal>()
    var meals = mutableStateListOf<Meal>()
    var filteredMeals = mutableStateListOf<Meal>()
    // Boolean mutable variables
    var isDialogOpen by mutableStateOf(false)
    var isLoading = mutableStateOf(true)
    // String mutable variables
    var searchText = mutableStateOf("")
    var dialogContext by mutableStateOf("")
    val mealMessage = mutableStateOf("")
    // Uri mutable variables
    var mealUri by mutableStateOf(Uri.EMPTY)

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
                async {mealRemoteDataSource.getAllMeals(meals)}.await()
                async { filteredMeals.addAll(meals) }.await()

            } catch (e: Exception) {
                meals.clear()
            }
            finally {
                isLoading.value = false
            }
        }
    }

    fun getMealById(id : Int)  {
        viewModelScope.launch(Dispatchers.IO) {
            meal.value = Meal()
            if (id != 0) {
                try {
                    meal.value = async { mealRemoteDataSource.getMealById(id = id) }.await()
                    user.value = async { mealRemoteDataSource.getUserById(id = meal.value.iduser.toInt() , token = TempSession.token) }.await()
                } catch (e: Exception) {
                    Log.e("Meals Error", e.message.toString())
                }
                finally {
                    isLoading.value = false
                }
            }
        }
    }


    fun getMyMeals()  {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                async { mealRemoteDataSource.getMyMeals(meals = myMeals, token = TempSession.token, id = TempSession.user.id) }.await()
            } catch (e: Exception) {
                myMeals.clear()
            }

        }
    }

    fun dateConverter(date: String,time : String): String {
        val timeList = date.split("T")
        val dateList = timeList[0].split("-")

        val hour = time.split("T")[1].subSequence(0,2)
        val minute = time.split("T")[1].subSequence(3,5)


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
        return "$day $month $year à $hour:$minute"
    }

    fun deleteMeal(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mealMessage.value = async { mealRemoteDataSource.deleteMeal(token = TempSession.token, id = id) }.await()
            } catch (e: Exception) {
                Log.e("Meals Error", e.message.toString())
            }
            finally {
                Log.e("Meals Delete Message", mealMessage.value)
                getMyMeals()
            }
        }

    }

    fun onAddMeal(mealBanner: File) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isLoading.value = true
                mealMessage.value = async { mealRemoteDataSource
                    .createMeal(
                        token = TempSession.token,
                        meal = meal.value,
                        mealBanner = mealBanner,
                    ) }.await().toString()
            } catch (e: Exception) {
                mealMessage.value = e.message.toString()
            }
            finally {
                isLoading.value = false
            }
        }
    }

    fun participateMeal(idrepas: Int, iduserOwner: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mealMessage.value = async { mealRemoteDataSource.participate(
                    token = TempSession.token,
                    mealId = idrepas,
                    userId = TempSession.user.id,
                    ownerId = iduserOwner,
                    motif= "Je veux participer ma man"
                    )
                }.await()
            } catch (e: Exception) {
                mealMessage.value = e.message.toString()
            }
        }
    }
}

