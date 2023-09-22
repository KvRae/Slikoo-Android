package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import okhttp3.MultipartBody
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.utils.TempSession.Companion.token

class MealRemoteDataSource() {

    suspend fun getAllMeals(meals: MutableList<Meal>) {
        try {
            meals.clear()
            val response = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java).getAllMeals()
            if (response.isSuccessful)
                response.body()?.meals?.let { meals.addAll(it) }
        }
        catch (e: Exception) {
            Log.e("Meals Error", e.message.toString())
        }
    }

    suspend fun createMeal(meal: Meal): String {
        return try {
            val response = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java)
                .createMeal(token = token,
                    meal= meal,
                    avatar = MultipartBody.Part.createFormData("avatar", meal.avatar))
            if (response.isSuccessful) response.body().toString()
            else response.errorBody().toString()
        }
        catch (e: Exception) {
            Log.e("Meals Error", e.message.toString())
            e.message.toString()
        }
    }

    suspend fun getMealById(id : Int): Meal {
        return try {
            val response = RetrofitInstance.getRetrofitInstance()
                .create(ApiServices::class.java).getMealById(id)
            if (response.code() == 200) response.body()?.meal!!
            else Meal()
        } catch (e: Exception) {
            Log.e("Meals Error", e.message.toString())
            Meal()
        }
    }



}