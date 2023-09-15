package slikoo.kvrae.slikoo.data.datasources.remote.dto

import com.google.gson.annotations.SerializedName
import slikoo.kvrae.slikoo.data.datasources.entities.Meal
import slikoo.kvrae.slikoo.data.datasources.entities.Notification

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