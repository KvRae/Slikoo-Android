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

class MealsViewModel: ViewModel() {


    private val mealRemoteDataSource = MealRemoteDataSource()
    // Objects
    var user = mutableStateOf(User())
    var meal = mutableStateOf(Meal())

    var motif by mutableStateOf("")
    // Lists
    var myMeals = mutableStateListOf<Meal>()

    var meals = mutableStateOf(mutableListOf<Meal>())
    var filteredMeals by mutableStateOf(mutableListOf<Meal>())
    // Boolean mutable variables
    var isDialogOpen by mutableStateOf(false)
    var isLoading = mutableStateOf(true)
    var isParticipating by mutableStateOf(false)
    var isError by mutableStateOf(false)
    var navigate by mutableStateOf(false)
    // String mutable variables
    var searchText = mutableStateOf("")
    var dialogContext by mutableStateOf("")
    var resCode by mutableStateOf(0)
    // Uri mutable variables
    var mealUri: Uri by mutableStateOf(Uri.parse(meal.value.avatarUrl.plus(meal.value.avatar)))

    init {
        getAllMeals()
    }

    fun filterMealsList(filter: String) {
        filteredMeals.clear()
        if (filter.isEmpty()) {
            filteredMeals.clear()
            filteredMeals.addAll(meals.value)
        }
        if (filter.isNotEmpty())
            for (meal in meals.value) {
                if (
                    meal.localisation.contains(filter, ignoreCase = true)
                    || meal.theme.contains(filter, ignoreCase = true)
                    || meal.genrenourriture.contains(filter, ignoreCase = true)
                    || meal.type.contains(filter, ignoreCase = true)
                    || meal.prix.contains(filter, ignoreCase = true)
                    || meal.nbr.contains(filter, ignoreCase = true)
                    || meal.description.contains(filter, ignoreCase = true)
                    || meal.date.contains(filter, ignoreCase = true)
                    || meal.heure.contains(filter, ignoreCase = true)
                )
                    filteredMeals.add(meal)
            }
    }

    fun getAllMeals()  {
        viewModelScope.launch {
            try {
                isError = false
                meals.value.clear()
                meals.value = async {mealRemoteDataSource.getAllMeals()}.await()
                async { filteredMeals.addAll(meals.value) }.await()

            } catch (e: Exception) {
                meals.value.clear()
                isError = true
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
                    isError = false
                    isLoading.value = true
                    meal.value = async { mealRemoteDataSource.getMealById(id = id) }.await()
                    user.value = async { mealRemoteDataSource.getUserById(id = meal.value.iduser.toInt() , token = TempSession.token) }.await()

                } catch (e: Exception) {
                    Log.e("Meals Error", e.message.toString())
                    isError = true
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
                isError = false
                async { mealRemoteDataSource.getMyMeals(meals = myMeals, token = TempSession.token, id = TempSession.user.id) }.await()
            } catch (e: Exception) {
                isError = true
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
        resCode = 0
        navigate = false
        viewModelScope.launch(Dispatchers.IO) {
            resCode = try {
                async {
                    mealRemoteDataSource.deleteMeal(
                        token = TempSession.token,
                        id = id
                    )
                }.await()

            } catch (e: Exception) {
                500
            } finally {
                if (resCode in 200..299) {
                    meals.value.remove(meal.value)
                    navigate = true
                }
            }
        }
    }

    fun onAddMeal(mealBanner: File) {
        resCode = 0
        viewModelScope.launch(Dispatchers.IO) {
            try {
                navigate = false
                isError = false
                isLoading.value = true
                resCode = async { mealRemoteDataSource
                    .createMeal(
                        token = TempSession.token,
                        meal = meal.value,
                        mealBanner = mealBanner,
                    ) }.await()
                val asyncList =async {
                    getAllMeals()
                }
                asyncList.await()
            } catch (e: Exception) {
                resCode = 500
            }
            finally {
                isLoading.value = false
                navigate = (resCode in 200..299)
            }
        }
    }

    fun onUpdateMeal(mealBanner: File, id: Int) {
        resCode = 0
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isError = false
                isLoading.value = true
                resCode = async { mealRemoteDataSource
                    .updateMeal(
                        token = TempSession.token,
                        meal = meal.value,
                        mealBanner = mealBanner,
                        id = id
                    ) }.await()
            } catch (e: Exception) {
                resCode = 500
            }
            finally {
                isLoading.value = false
            }
        }
    }

    fun participateMeal(idrepas: Int, iduserOwner: Int) {
        resCode = 0
        viewModelScope.launch(Dispatchers.IO) {
            navigate = false
            isLoading.value = true
            resCode = try {
                isError = false
                async { mealRemoteDataSource.participate(
                    token = TempSession.token,
                    mealId = idrepas,
                    userId = TempSession.user.id,
                    ownerId = iduserOwner,
                    motif= motif
                )
                }.await()
            } catch (e: Exception) {
                isError = true
                500
            }
            finally {
                if (resCode == 200) {
                    isParticipating = true
                    navigate = true
                }
                isLoading.value = false
            }
        }
    }

    fun getMealsByCategory(filter: String): MutableList<Meal> {
        return meals.value.filter { it.localisation.contains(filter, ignoreCase = true) }.toMutableList()
    }

    fun checkIfParticipating(idrepas: Int, iduser: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                isError = false
                val isParticipatingResponse  = async { mealRemoteDataSource.ifUserParticipated(
                    token = TempSession.token,
                    mealId = idrepas,
                    userId = iduser)
                }.await()
                isParticipating = async { isParticipatingResponse.result }.await()
            } catch (e: Exception) {
                isError = true
            }
        }
    }
}

