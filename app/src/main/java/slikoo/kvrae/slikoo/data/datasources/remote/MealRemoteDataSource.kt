package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.entities.Meal

class MealRemoteDataSource() {

    suspend fun getAllMeals(meals: MutableList<Meal>) {
        meals.clear()
        val response = RetrofitInstance.getRetrofitInstance().create(ApiServices::class.java).getAllMeals()
        try {
            if (response.isSuccessful)
                response.body()?.meals?.let { meals.addAll(it) }
        }
        catch (e: Exception) {
            Log.e("Meals Error", e.message.toString())
        }


    }
}