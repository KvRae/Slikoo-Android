package slikoo.kvrae.slikoo.data.api


import android.util.Log
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.dto.LoginResponse
import slikoo.kvrae.slikoo.data.datasources.remote.dto.MealDetailsResponse
import slikoo.kvrae.slikoo.data.datasources.remote.dto.MealResponse
import slikoo.kvrae.slikoo.data.datasources.remote.dto.NotificationsResponse
import java.util.concurrent.TimeUnit


interface ApiServices {

    /************************** Get Requests **************************/


    @Headers("Content-Type: application/json")
    @GET("getAllRepas")
    suspend fun getAllMeals(): Response<MealResponse>

    @Headers("Content-Type: application/json")
    @GET("getrepasdetailsbyId/{id}")
    suspend fun getMealById(@Path("id") id: Int): Response<MealDetailsResponse>

    @Headers("Content-Type: application/json")
    @GET("GetAllNotifications/{email}")
    suspend fun getNotificationsByEmail(@Header("Authorization") token: String, @Path("email") email: String): Response<NotificationsResponse>

    @Headers("Content-Type: application/json", "Accept: application/json")
    @POST("get-user-by-email/{email}")
    suspend fun getUserByEmail(@Header("Authorization") token: String, @Path("email") email: String): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("getUsetdetailsbyId/{id}")
    suspend fun getUserById(@Header("Authorization") token: String, @Path("id") id: Int): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("diplsayfeedbackbyid/{id}")
    suspend fun getFeedbackById(@Header("Authorization") token: String, @Path("id") id: Int): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("displaymysubmitedfeedBacks/{id}")
    suspend fun getMySubmittedFeedbacks(@Header("Authorization") token: String, @Path("id") id: Int): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("displaymyfeedback/{id}")
    suspend fun getMyFeedback(@Header("Authorization") token: String, @Path("id") id: Int): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("displayfeedbackbyrepas/{id}")
    suspend fun getFeedbackByMealId(@Header("Authorization") token: String, @Path("id") id: Int): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("getlistParticipantConfirmer/{id}")
    suspend fun getConfirmedParticipants(@Header("Authorization") token: String, @Path("id") id: Int): Response<UserResponse>





    /************************** Post Requests **************************/

    @Headers("Content-Type: application/json")
    @POST("addRib")
    suspend fun addRib(@Header("Authorization") token: String, @Body ribRequest: RibRequest): Response<Int>

    @Headers("Content-Type: application/json")
    @POST("addrepas")
    suspend fun createMeal(@Header("Authorization") token: String, @Body meal: Meal, avatar: MultipartBody.Part): Response<String>

    @Headers("Content-Type: application/json")
    @POST("mobile-login")
    suspend fun login(@Body user: LoginRequest): Response<LoginResponse>

    @Multipart
    @Headers("Content-Type: application/json")
    @POST("register")
    suspend fun register(@Body user: User, @Part avatar: MultipartBody.Part, @Part cinavatar: MultipartBody.Part ): Response<String>

    @Headers("Content-Type: application/json", "Accept: application/html")
    @POST("resetpwd")
    suspend fun forgetPassword(@Body forgetPasswordRequest: ForgetPasswordRequest): Response<Int>









    /************************** Put & Patch Requests **************************/

    /************************** Delete Requests **************************/










}

data class RibRequest(
    val email: String,
    val rib: String
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class ForgetPasswordRequest(
    val email: String
)

data class UserResponse(
    val user: User,
)




class RetrofitInstance {
    companion object {
        private const val BASE_URL: String = "https://slikoo.com/api/"
        private val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("Retrofit", message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS) // Connection timeout
            .readTimeout(10, TimeUnit.SECONDS)    // Read timeout
            .writeTimeout(10, TimeUnit.SECONDS)   // Write timeout
            .retryOnConnectionFailure(false)
            .addInterceptor(loggingInterceptor)
            .build()


        private var retrofit: Retrofit? = null
        // retrofit singleton instance
        fun getRetrofitInstance(): Retrofit {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }
    }
}