package slikoo.kvrae.slikoo.data.datasources.dto

import com.google.gson.annotations.SerializedName
import slikoo.kvrae.slikoo.data.datasources.entities.Feedback
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.entities.Notification
import slikoo.kvrae.slikoo.data.datasources.entities.Reservation
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.entities.UserDetails

data class MealResponse(
    @SerializedName("repasList")
    val meals: List<Meal>,
    @SerializedName("repas")
    val meal: Meal,
    @SerializedName("message")
    val message: String
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
    val feedbacks: List<Feedback>
)

data class UserDetailsRequest(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("idusermain")
    var idusermain: String? = null,
    @SerializedName("iduser")
    var iduser: String? = null,
    @SerializedName("fumeur")
    var fumeur: String? = null,
    @SerializedName("alcohol")
    var alcohol: String? = null,
    @SerializedName("cherche")
    var cherche: String? = null,
    @SerializedName("algalimentaire")
    var algalimentaire: String = "",
    @SerializedName("centreinteret")
    var centreinteret: String? = null,
    @SerializedName("langues")
    var langues: String? = null,
    @SerializedName("chercherplus")
    var chercherplus: String? = null,
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
    @SerializedName("newpassword")
    val newPassword: String
)

data class UpdatePasswordResponse(
    val message: String,
    val error : String
)

data class ResponseSlk(
    val code: Int? = null,
    val message: String = "",
    val error : String = "",
    @SerializedName("Result")
    val result : Boolean = false,
    @SerializedName("Status")
    val status : String = "",
    @SerializedName("User updated")
    val user : User = User(),
)

data class ReservationsResponse(
    @SerializedName("Reservation list")
    val reservations: MutableList<Reservation>
)

data class ReservationRequest(
    @SerializedName("iduserdemander")
    val idUser: Int,
)

data class InvitationRequest (
    @SerializedName("iduserdemander")
    var idDemander: String? = null,
    @SerializedName("iduserowner")
    var idOwner: String? = null,
    @SerializedName("idrepas")
    var idMeal: String? = null,
    @SerializedName("informationComp")
    var informationComp: String? = null

)

data class FeedbackResponse(
    @SerializedName("Feedbacks")
    val feedbacks: MutableList<Feedback>
)

data class FeedbackRequest(
    @SerializedName("idusersender")
    var idUserSender: Int,
    @SerializedName("iduserreceiver")
    val idUserReceiver: String,
    @SerializedName("idrepas")
    val idMeal: Int,
    @SerializedName("commantaire")
    val comment: String,
    @SerializedName("nbretoile")
    val rating: Int,
    @SerializedName("image_files")
    val imageProvider: List<String>
)

