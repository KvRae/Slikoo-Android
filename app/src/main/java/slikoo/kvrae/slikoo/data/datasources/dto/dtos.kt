package slikoo.kvrae.slikoo.data.datasources.dto

import com.google.gson.annotations.SerializedName
import slikoo.kvrae.slikoo.data.datasources.entities.FeedBack
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.entities.Notification
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.entities.UserDetails

data class MealResponse(
    @SerializedName("repasList")
    val meals: List<Meal>
)

data class MealDetailsResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("repas")
    val meal: Meal
)

data class LoginResponse(
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("message")
    val message: String? = null
)

data class NotificationsResponse(
    @SerializedName("Notification")
    val notifications: List<Notification>
)

data class RibRequest(
    val email: String,
    val rib: String
)

data class LoginRequest(
    val username: String,
    val password: String
)

data class ForgetPasswordRequest(
    val email: String,
    val newpassword: String,
    val digitcode: String
)

data class ForgetPasswordResponse(
    val message: String,
    val error : String
)

data class UserResponse(
    val user: User,
    @SerializedName("User")
    val user1 : User,
    val message: String
)

data class FeedBacksResponse(
    @SerializedName("Feedbacks")
    val feedbacks: List<FeedBack>
)

data class UserDetailsRequest(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("idusermain")
    var idusermain: String? = null,
    @SerializedName("fumeur")
    var fumeur: String? = null,
    @SerializedName("alcohol")
    var alcohol: String? = null,
    @SerializedName("cherche")
    var cherche: ArrayList<String> = arrayListOf(),
    @SerializedName("algalimentaire")
    var algalimentaire: ArrayList<String> = arrayListOf(),
    @SerializedName("centreinteret")
    var centreinteret: ArrayList<String> = arrayListOf(),
    @SerializedName("langues")
    var langues: ArrayList<String> = arrayListOf(),
    @SerializedName("chercherplus")
    var chercherplus: ArrayList<String> = arrayListOf(),
    @SerializedName("Facebooklink")
    var Facebooklink: String? = null,
    @SerializedName("InstagramLink")
    var InstagramLink: String? = null,
    @SerializedName("TwitterLink")
    var TwitterLink: String? = null,
    @SerializedName("LinkedinLink")
    var LinkedinLink: String? = null
)

data class MealRequest(
    val meal: Meal,
    val avatar: String
)

data class ParticiapteRequest(
    val motif: String
)

data class UserDetailResponse(
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("Userdetails")
    var userDetails: UserDetails? = UserDetails(),
    @SerializedName("User details updated")
    var userDetailsUpdated: UserDetails? = UserDetails()
)

data class UpdatePasswordRequest(
    @SerializedName("id")
    val id : String,
    @SerializedName("oldpassword")
    val oldPassword: String,
    @SerializedName("newPassword")
    val newPassword: String
)

data class UpdatePasswordResponse(
    val message: String,
    val error : String
)

data class ResponseSlk(
    val message: String,
    val error : String,
    @SerializedName("User updated")
    val user : User
)



