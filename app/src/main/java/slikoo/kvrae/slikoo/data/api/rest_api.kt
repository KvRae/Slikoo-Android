package slikoo.kvrae.slikoo.data.api


import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import slikoo.kvrae.slikoo.data.dto.LoginResponse
import slikoo.kvrae.slikoo.data.dto.MealResponse
import slikoo.kvrae.slikoo.data.dto.NotificationsResponse
import slikoo.kvrae.slikoo.data.entities.User

interface ApiServices {

    @Headers("Content-Type: application/json")
    @GET("getAllRepas")
    suspend fun getAllMeals(): Response<MealResponse>

    @Headers("Content-Type: application/json")
    @POST("mobile-login")
    suspend fun login(@Body user: LoginRequest): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("get-user-by-email")
    suspend fun getUserByEmail(@Header("Authorization") token: String, @Body user: User): Response<User>

    @Headers("Content-Type: application/json")
    @GET("GetAllNotifications")
    suspend fun getNotificationsByEmail(email: String): Response<NotificationsResponse>

    @Headers("Content-Type: application/json")
    @POST("addRib")
    suspend fun addRib( @Body user: User): Response<String>

}

data class LoginRequest(
    val username: String,
    val password: String
)




class RetrofitInstance {
    companion object {
        private const val BASE_URL: String = "https://slikoo.com/api/"
        private val loggingInterceptor = HttpLoggingInterceptor { message ->
            Log.d(
                "Retrofit",
                message
            )
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()


        // retrofit instance
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}