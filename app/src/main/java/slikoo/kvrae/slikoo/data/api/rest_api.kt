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
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import slikoo.kvrae.slikoo.data.datasources.dto.FeedBacksResponse
import slikoo.kvrae.slikoo.data.datasources.dto.FeedbackResponse
import slikoo.kvrae.slikoo.data.datasources.dto.ForgetPasswordRequest
import slikoo.kvrae.slikoo.data.datasources.dto.ForgetPasswordResponse
import slikoo.kvrae.slikoo.data.datasources.dto.InvitationRequest
import slikoo.kvrae.slikoo.data.datasources.dto.LoginRequest
import slikoo.kvrae.slikoo.data.datasources.dto.LoginResponse
import slikoo.kvrae.slikoo.data.datasources.dto.MealDetailsResponse
import slikoo.kvrae.slikoo.data.datasources.dto.MealResponse
import slikoo.kvrae.slikoo.data.datasources.dto.NotificationsResponse
import slikoo.kvrae.slikoo.data.datasources.dto.ParticiapteRequest
import slikoo.kvrae.slikoo.data.datasources.dto.ReservationRequest
import slikoo.kvrae.slikoo.data.datasources.dto.ReservationsResponse
import slikoo.kvrae.slikoo.data.datasources.dto.ResponseSlk
import slikoo.kvrae.slikoo.data.datasources.dto.RibRequest
import slikoo.kvrae.slikoo.data.datasources.dto.UpdatePasswordRequest
import slikoo.kvrae.slikoo.data.datasources.dto.UpdatePasswordResponse
import slikoo.kvrae.slikoo.data.datasources.dto.UserDetailResponse
import slikoo.kvrae.slikoo.data.datasources.dto.UserDetailsRequest
import slikoo.kvrae.slikoo.data.datasources.dto.UserResponse
import slikoo.kvrae.slikoo.data.datasources.entities.Feedback
import slikoo.kvrae.slikoo.data.datasources.entities.Invitation


interface ApiServices {

    /************************** Get Requests **************************/

    //----------------------- Meals ----------------------------------//
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
    //----------------------- User ----------------------------------//
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
    @GET("getUsetdetailsbyId/{id}")
    suspend fun getUserDetailsById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>

    //----------------------- Invitations ----------------------------------//
    @Headers("Content-Type: application/json")
    @GET("getInvitation/{id}")
    suspend fun getInvitations(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<List<Invitation>>


    //----------------------- Feedbacks ----------------------------------//
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
    ): Response<FeedbackResponse>

    @Headers("Content-Type: application/json")
    @GET("displaymyfeedback/{id}")
    suspend fun getMyFeedbacks(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<FeedBacksResponse>

    @Headers("Content-Type: application/json")
    @GET("displayfeedbackbyrepas/{idrepas}")
    suspend fun getFeedbacksByMeal(
        @Header("Authorization") token: String,
        @Path("idrepas") id: Int
    ): Response<FeedBacksResponse>


    //----------------------- Participants ----------------------------------//

    @Headers("Content-Type: application/json")
    @GET("getlistParticipantConfirmer/{id}")
    suspend fun getConfirmedParticipants(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserResponse>

    @Headers("Content-Type: application/json")
    @GET("ifuserisparticipated/{idrepas}/{iduser}")
    suspend fun ifUserIsParticipated(
        @Header("Authorization") token: String,
        @Path("idrepas") idrepas: Int,
        @Path("iduser") iduser: Int
    ): Response<ResponseSlk>
    //------------------------ UserDeatils ----------------------------------//
    @Headers("Content-Type: application/www-form-urlencoded")
    @GET("getUserdetailsbyId/{id}")
    suspend fun getUserDetailsId(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Response<UserDetailResponse>




    /************************** Post Requests **************************/




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
    ): Response<ResponseSlk>

    //----------------------------------- Forget Password -----------------------------------//

    @Headers("Content-Type: application/json", "Accept: application/html")
    @POST("updatepasswordDigitcode/{email}")
    suspend fun forgetPassword(@Path("email") email: String): Response<ForgetPasswordResponse>


    @Headers("Content-Type: application/json")
    @POST("verifyDigitcode/{email}/{Digitcode}")
    suspend fun verifyCode(
        @Path("email") email: String,
        @Path("Digitcode") code: String
    ): Response<ForgetPasswordResponse>

    @Headers("Content-Type: application/json")
    @POST("updatepasswordafterdigitcode")
    suspend fun updatePasswordAfterCode(
        @Body updatePasswordRequest: ForgetPasswordRequest
    ): Response<ForgetPasswordResponse>

    //----------------------------------- User Details -----------------------------------//
    @Headers("Content-Type: application/json")
    @POST("adduserdetailsJsonBody")
    suspend fun addUserDetails(
        @Header("Authorization") token: String,
        @Body user: UserDetailsRequest
    ): Response<UserDetailResponse>

    @Headers("Content-Type: application/json")
    @POST("updateuserdetailsJsonBody")
    suspend fun updateUserDetails(
        @Header("Authorization") token: String,
        @Body user: UserDetailsRequest
    ): Response<UserDetailResponse>


    //----------------------------------- Reservation -----------------------------------//
    @Headers("Content-Type: application/json")
    @POST("getreservations")
    suspend fun getReservations(
        @Header("Authorization") token: String,
        @Body reservation : ReservationRequest
    ): Response<ReservationsResponse>



    @Headers("Content-Type: application/json")
    @POST("participateRepas/{idrepas}/{iduserconnected}/{iduserOwner}")
    suspend fun participateMeal(@Header("Authorization") token: String,
                                @Path("idrepas") idrepas: Int,
                                @Path("iduserconnected") iduserconnected: Int,
                                @Path("iduserOwner") iduserOwner: Int,
                                @Body motif: ParticiapteRequest
    ): Response<ResponseSlk>

    @Headers("Content-Type: application/json")
    @POST("cancelreservationFromOwner/{idrepas}/{iddemander}")
    suspend fun declineReservation(
        @Header("Authorization") token: String,
        @Path("idrepas") idMeal: Int,
        @Path("iddemander") idUser: Int
    ): Response<ResponseSlk>

    //----------------------------- Invitation -----------------------------------//

    @Headers("Content-Type: application/json")
    @POST("cancelinvitationFromOwner/{idrepas}/{iddemander}")
    suspend fun declineInvitation(
        @Header("Authorization") token: String,
        @Path("idrepas") idMeal: Int,
        @Path("iddemander") idUser: Int
    ): Response<ResponseSlk>


    @Headers("Content-Type: application/json")
    @POST("acceptinvitation")
    suspend fun acceptInvitation(
        @Header("Authorization") token: String,
        @Body body : InvitationRequest
    ): Response<ResponseSlk>


    @Headers("Content-Type: application/json")
    @POST("makefeedback")
    suspend fun makeFeedback(
        @Header("Authorization") token: String,
        @Body body : Feedback
    ): Response<ResponseSlk>




    /************************** Put Requests **************************/

    @Headers("Content-Type: application/json")
    @PUT("updatepassword")
    suspend fun updatePassword(
        @Header("Authorization") token: String,
        @Body updatePasswordRequest: UpdatePasswordRequest
    ): Response<UpdatePasswordResponse>

    @Multipart
    @POST("updateUser")
    suspend fun updateUser(
        @Header("Authorization") token: String,
        @Query("iduser") id: Int,
        @Part("email") email: RequestBody,
        @Part("nom") nom: RequestBody,
        @Part("prenom") prenom: RequestBody,
        @Part("numtel") numtel: RequestBody,
        @Part("adressepostal") adressepostal: RequestBody,
        @Part("ville") ville: RequestBody,
        @Part("codepostal") codepostal: RequestBody,
        @Part("description") description: RequestBody,
        @Part avatar: MultipartBody.Part? = null,
        @Part banner : MultipartBody.Part? = null,
        @Part("sex") sexe: RequestBody // blame the backend developer for this typo
    ): Response<ResponseSlk>

    @Headers("Content-Type: application/json")
    @PUT("updaterib")
    suspend fun addRib(
        @Header("Authorization") token: String,
        @Body ribRequest: RibRequest
    ): Response<Int>



    /************************** Delete Requests **************************/
    @Headers("Content-Type: application/json")
    @DELETE("deleterepas/{idrepas}")
    suspend fun deleteMeal(
        @Header("Authorization") token: String,
        @Path("idrepas") id: Int
    ): Response<ResponseSlk>
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

