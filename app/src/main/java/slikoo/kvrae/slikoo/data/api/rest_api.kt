package slikoo.kvrae.slikoo.data.api


import android.util.Log
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import slikoo.kvrae.slikoo.data.datasources.remote.dto.ForgetPasswordRequest
import slikoo.kvrae.slikoo.data.datasources.remote.dto.LoginRequest
import slikoo.kvrae.slikoo.data.datasources.remote.dto.LoginResponse
import slikoo.kvrae.slikoo.data.datasources.remote.dto.MealDetailsResponse
import slikoo.kvrae.slikoo.data.datasources.remote.dto.MealResponse
import slikoo.kvrae.slikoo.data.datasources.remote.dto.NotificationsResponse
import slikoo.kvrae.slikoo.data.datasources.remote.dto.ParticiapteRequest
import slikoo.kvrae.slikoo.data.datasources.remote.dto.RibRequest
import slikoo.kvrae.slikoo.data.datasources.remote.dto.UserResponse


interface ApiServices {

    /************************** Get Requests **************************/


    @Headers("Content-Type: application/json")
    @GET("get-all-repas")
    suspend fun getAllMeals(): Response<MealResponse>

    @Headers("Content-Type: application/json")
    @GET("getallrepasbyuserId/{id}")
    suspend fun getMyMeals(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<MealResponse>

    @Headers("Content-Type: application/json")
    @GET("getrepasdetailsbyId/{id}")
    suspend fun getMealById(
        @Path("id") id: Int
    ): Response<MealDetailsResponse>

    @Headers("Content-Type: application/json")
    @GET("GetAllNotifications/{email}")
    suspend fun getNotificationsByEmail(
        @Header("Authorization") token: String,
        @Path("email") email: String
    ): Response<NotificationsResponse>

    @Headers("Content-Type: application/json")
    @POST("get-user-by-email/{email}")
    suspend fun getUserByEmail(
        @Header("Authorization") token: String,
        @Path("email") email: String
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("getuserbyID/{id}")
    suspend fun getUserById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("getInvitation/{id}")
    suspend fun getInvitations(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("getUsetdetailsbyId/{id}")
    suspend fun getUserDetailsById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("diplsayfeedbackbyid/{id}")
    suspend fun getFeedbackById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("displaymysubmitedfeedBacks/{id}")
    suspend fun getMySubmittedFeedbacks(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("displaymyfeedback/{id}")
    suspend fun getMyFeedback(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("displayfeedbackbyrepas/{id}")
    suspend fun getFeedbackByMealId(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("getlistParticipantConfirmer/{id}")
    suspend fun getConfirmedParticipants(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>





    /************************** Post Requests **************************/

    @Headers("Content-Type: application/json")
    @POST("addRib")
    suspend fun addRib(
        @Header("Authorization") token: String,
        @Body ribRequest: RibRequest
    ): Response<Int>

    @Multipart
    @POST("addrepas")
    suspend fun createMeal(
        @Header("Authorization") token: String,
        @Part("localisation") localisation: RequestBody,
        @Part("type") type: RequestBody,
        @Part("theme") theme: RequestBody,
        @Part("genres") genres: RequestBody,
        @Part("genrenourriture") genrenourriture: RequestBody,
        @Part("description") description: RequestBody,
        @Part("nbrPerson") nbrPersonne: RequestBody,
        @Part("prix") prix: RequestBody,
        @Part("date") date: RequestBody,
        @Part("heure") heure: RequestBody,
        @Part avatar: MultipartBody.Part,
    ): Response<String>

    @Headers("Content-Type: application/json")
    @POST("mobile-login")
    suspend fun login(@Body user: LoginRequest): Response<LoginResponse>

    @Multipart
    @POST("register")
    suspend fun register(
        @Part("email") email: RequestBody,
        @Part("nom") nom: RequestBody,
        @Part("prenom") prenom: RequestBody,
        @Part("numtel") numtel: RequestBody,
        @Part("password") password: RequestBody,
        @Part("adressepostal") adressepostal: RequestBody,
        @Part("ville") ville: RequestBody,
        @Part("codepostal") codepostal: RequestBody,
        @Part("description") description: RequestBody,
        @Part avatar: MultipartBody.Part,
        @Part cinavatar: MultipartBody.Part,
        @Part("sex") sexe: RequestBody // blame the backend developer for this typo
    ): Response<String>

    @Headers("Content-Type: application/json", "Accept: application/html")
    @POST("resetpwd")
    suspend fun forgetPassword(@Body forgetPasswordRequest: ForgetPasswordRequest): Response<Int>


    @Headers("Content-Type: application/json")
    @POST("participateRepas/{idrepas}/{iduserconnected}/{iduserOwner}")
    suspend fun participateMeal(@Header("Authorization") token: String,
                                @Path("idrepas") idrepas: Int,
                                @Path("iduserconnected") iduserconnected: Int,
                                @Path("iduserOwner") iduserOwner: Int,
                                @Body motif: ParticiapteRequest
    ): Response<String>
    /************************** Put Requests **************************/


    /************************** Delete Requests **************************/
    @Headers("Content-Type: application/json")
    @DELETE("deleterepas/{idreoas}")
    suspend fun deleteMeal(
        @Header("Authorization") token: String,
        @Path("idreoas") id: Int
    ): Response<String>
}






class RetrofitInstance {
    companion object {
        private const val BASE_URL: String = "https://slikoo.com/api/"
        private val loggingInterceptor = HttpLoggingInterceptor { message -> Log.d("Retrofit", message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(loggingInterceptor)
//            .callTimeout(15, java.util.concurrent.TimeUnit.SECONDS)
//            .connectTimeout(5, java.util.concurrent.TimeUnit.SECONDS)
//            .readTimeout(15, java.util.concurrent.TimeUnit.SECONDS)
//            .writeTimeout(15, java.util.concurrent.TimeUnit.SECONDS)
//            .pingInterval(5, java.util.concurrent.TimeUnit.SECONDS)
            .retryOnConnectionFailure(false)
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

