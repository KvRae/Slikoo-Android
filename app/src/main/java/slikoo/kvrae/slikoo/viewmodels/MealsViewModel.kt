package slikoo.kvrae.slikoo.viewmodels

import android.net.Uri
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import slikoo.kvrae.slikoo.R
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.MealRemoteDataSource
import slikoo.kvrae.slikoo.utils.TempSession
import java.io.File

class MealsViewModel: ViewModel() {


    private val mealRemoteDataSource = MealRemoteDataSource()
    // Objects
    val user = mutableStateOf(User())
    val meal = mutableStateOf(Meal())

    val motif = mutableStateOf("")
    val meals = mutableStateListOf<Meal>()

    val filteredMeals = mutableStateListOf<Meal>()
    // Boolean mutable variables
    val isDialogOpen = mutableStateOf(false)
    val isLoading = mutableStateOf(true)
    private val isParticipating = mutableStateOf(false)
    val isError = mutableStateOf(false)
    private val isDeleted = mutableStateOf(false)
    val navigate = mutableStateOf(false)
    // String mutable variables
    val searchText = mutableStateOf("")
    val dialogContext = mutableStateOf("")
    private val resCode = mutableStateOf(0)
    val participationState = mutableStateOf("")
    val navigationMessage = mutableStateOf(R.string.empty_string)
    // Uri mutable variables
    val mealUri: MutableState<Uri> = mutableStateOf(Uri.parse(meal.value.avatarUrl.plus(meal.value.avatar)))

    init {
        getAllMeals()
    }

    fun filterMealsList(filter: String) {
        filteredMeals.removeAll(filteredMeals)
        if (filter.isEmpty()) {
            filteredMeals.clear()
            filteredMeals.addAll(meals)
        }
        if (filter.isNotEmpty())
            for (meal in meals) {
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
                navigate.value = false
                isError.value  = false
                isLoading.value = true
                val allMeals = async { mealRemoteDataSource.getAllMeals() }.await()
                meals.clear()
                meals.addAll(allMeals)
                filteredMeals.clear()
                filteredMeals.addAll(allMeals)



            } catch (e: Exception) {
                meals.clear()
                isError.value  = true
            }
            finally {
                isLoading.value = false
                if (meals.isNotEmpty()){meals.shuffle()}

            }
        }
    }

    fun getMealById(id : Int)  {
        viewModelScope.launch {
            meal.value = Meal()
            if (id != 0) {
                try {
                    navigate.value  = false
                    isError.value  = false
                    isLoading.value = true
                    meal.value = async { mealRemoteDataSource.getMealById(id = id) }.await()
                    user.value = async { mealRemoteDataSource.getUserById(
                        id = meal.value.iduser.toInt() ,
                        token = TempSession.token) }.await()
                } catch (e: Exception) {
                    Log.e("Meals Error", e.message.toString())
                    isError.value  = true
                }
                finally {
                    isLoading.value = false
                }
            }
        }
    }




    fun deleteMeal(id: Int) {
        resCode.value  = 0
        viewModelScope.launch {
            resCode.value  = try {
                isDeleted.value  = false
                navigate.value  = false
                async {
                    mealRemoteDataSource.deleteMeal(
                        token = TempSession.token,
                        id = id
                    )
                }.await()

            } catch (e: Exception) {
                navigationMessage.value = R.string.server_error
                500
            } finally {
                if (resCode.value  in 200..299) {
                    meals.remove(meal.value)
                    isDeleted.value  = true
                    navigationMessage.value = R.string.meal_deleted
                }
                navigate.value  = true
            }
        }
    }

    fun onAddMeal(mealBanner: File) {
        resCode.value  = 0
        viewModelScope.launch {
            try {
                navigate.value  = false
                isError.value  = false
                isLoading.value = true
                resCode.value  = async { mealRemoteDataSource
                    .createMeal(
                        token = TempSession.token,
                        meal = meal.value,
                        mealBanner = mealBanner,
                    ) }.await()
            } catch (e: Exception) {
                resCode.value  = 500
            }
            finally {
                isLoading.value = false
                navigate.value  = (resCode.value in 200..299)
            }
        }
    }

    fun onUpdateMeal(mealBanner: File, id: Int) {
        resCode.value  = 0
        viewModelScope.launch {
            try {
                isError.value = false
                isLoading.value = true
                resCode.value = async { mealRemoteDataSource
                    .updateMeal(
                        token = TempSession.token,
                        meal = meal.value,
                        mealBanner = mealBanner,
                        id = id
                    ) }.await()
            } catch (e: Exception) {
                resCode.value = 500
            }
            finally {
                isLoading.value = false
            }
        }
    }

    fun participateMeal(idMeal: Int, idUserOwner: Int) {
        resCode.value = 0
        viewModelScope.launch {
            navigate.value = false
            isLoading.value = true
            resCode.value = try {
                isError.value = false
                async { mealRemoteDataSource.participate(
                    token = TempSession.token,
                    mealId = idMeal,
                    userId = TempSession.user.id,
                    ownerId = idUserOwner,
                    motif= motif.value
                )
                }.await()
            } catch (e: Exception) {
                isError.value = true
                500
            }
            finally {
                if (resCode.value in 200 .. 299) {
                    isParticipating.value = true
                    meal.value = async { mealRemoteDataSource.getMealById(id = idMeal) }.await()
                    navigationMessage.value = R.string.participate_msg
                }
                isLoading.value = false
                navigate.value = true
            }
        }
    }

    fun getMealsByLocalisation(filter: String, mealsFiltered: MutableList<Meal>) {
        viewModelScope.launch {
            try {
                isError.value = false
                isLoading.value = true

                mealsFiltered.clear()
                val allMeals = async {
                    mealRemoteDataSource.getAllMeals()
                }.await()

                val filteredMeals = async {
                    allMeals.filter { meal ->
                        meal.localisation.contains(filter, ignoreCase = true)
                                || meal.theme.contains(filter, ignoreCase = true)
                    }
                }.await()

                mealsFiltered.addAll(filteredMeals)

            } catch (e: Exception) {
                isError.value = true
                mealsFiltered.clear()
            } finally {
                isLoading.value = false
            }
        }
    }

    fun checkIfParticipating(idMeal: Int, idUser: Int) {
        viewModelScope.launch {
            try {
                isError.value = false
                val isParticipatingResponse  = async { mealRemoteDataSource.ifUserParticipated(
                    token = TempSession.token,
                    mealId = idMeal,
                    userId = idUser)
                }.await()
                isParticipating.value = async { isParticipatingResponse.result }.await()
            } catch (e: Exception) {
                isError.value = true
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

    fun checkParticipationState() : String {
        return when{
            isParticipating.value && meal.value.iduser != TempSession.user.id.toString()-> {
                 "Participated"
            }
            meal.value.iduser == TempSession.user.id.toString() -> {
                 "Owner"
            }
            else -> {
                 "Not Participated"
            }
        }
    }
}