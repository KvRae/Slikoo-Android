package slikoo.kvrae.slikoo.data.datasources.remote

import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import slikoo.kvrae.slikoo.data.api.ApiServices
import slikoo.kvrae.slikoo.data.api.RetrofitInstance
import slikoo.kvrae.slikoo.data.datasources.dto.ParticiapteRequest
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.entities.User
import java.io.File

class MealRemoteDataSource {

    suspend fun getAllMeals(meals: MutableList<Meal>) {
        try {
            meals.clear()
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .getAllMeals()
            if (response.isSuccessful)
                response.body()?.meals?.let { meals.addAll(it) }
        }
        catch (e: Exception) {
            Log.e("Meals Error", e.message.toString())
        }
    }

    suspend fun getMyMeals(meals: MutableList<Meal>, token: String, id: Int) {
        try {
            meals.clear()
            val response = RetrofitInstance.getRetrofitInstance()
                .create(ApiServices::class.java)
                .getMyMeals(token = "Bearer $token", id = id)
            if (response.isSuccessful)
                response.body()?.meals?.let { meals.addAll(it) }
        }
        catch (e: Exception) {
            Log.e("Meals Error", e.message.toString())
        }
    }

    suspend fun createMeal(
        token: String,
        meal: Meal,
        mealBanner: File,
    ): Int {
        val bannerRequestBody = mealBanner.asRequestBody("multipart/form-data".toMediaTypeOrNull())
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .createMeal(token= "Bearer $token",
                    localisation = meal.localisation.toRequestBody("text/plain".toMediaTypeOrNull()),
                    type = meal.type.toRequestBody("text/plain".toMediaTypeOrNull()),
                    theme = meal.theme.toRequestBody("text/plain".toMediaTypeOrNull()),
                    genres = meal.genre.toRequestBody("text/plain".toMediaTypeOrNull()),
                    genrenourriture = meal.genrenourriture.toRequestBody("text/plain".toMediaTypeOrNull()),
                    description = meal.description.toRequestBody("text/plain".toMediaTypeOrNull()),
                    prix = meal.prix.toRequestBody("text/plain".toMediaTypeOrNull()),
                    nbrPersonne = meal.nbr.toRequestBody("text/plain".toMediaTypeOrNull()),
                    date = meal.date.toRequestBody("text/plain".toMediaTypeOrNull()),
                    heure = meal.heure.toRequestBody("text/plain".toMediaTypeOrNull()),
                    avatar = MultipartBody.Part.createFormData("avatar", mealBanner.name, bannerRequestBody),)
            if (response.isSuccessful) {
                200
            }
            else {
                Log.e("Meals Error", response.errorBody().toString())
                400
            }
        }
        catch (e: Exception){
            Log.e("Meals Error", e.message.toString())
            500
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

    suspend fun getUserById(id: Int, token: String): User {
        return try {
            val response = RetrofitInstance.getRetrofitInstance()
                .create(ApiServices::class.java)
                .getUserById(token = "Bearer $token", id = id)
            if (response.code() == 200) response.body()?.user1!!
            else User()
        } catch (e: Exception) {
            Log.e("Meals Error", e.message.toString())
            User()
        }
    }

    suspend fun deleteMeal(token: String,id: Int): Int {
        return try {
            val response = RetrofitInstance.getRetrofitInstance()
                .create(ApiServices::class.java).deleteMeal(token = "Bearer $token", id = id)
            if (response.isSuccessful) 200
            else 400
        } catch (e: Exception) {
            Log.e("Meals Error", e.message.toString())
            500
        }
    }

    suspend fun participate(
        token: String,
        mealId: Int,
        userId: Int,
        ownerId: Int,
        motif : String
    ): Int{
        return try {
            val response = RetrofitInstance
                .getRetrofitInstance()
                .create(ApiServices::class.java)
                .participateMeal("Bearer $token",mealId,userId,ownerId, ParticiapteRequest(motif))
            if (response.isSuccessful) 200
            else 400
        }
        catch (e: Exception) {
            500
        }

    }

    fun updateMeal(token: String, meal: Meal, mealBanner: File, id: Int): Int {
        TODO("Not yet implemented")
    }


}