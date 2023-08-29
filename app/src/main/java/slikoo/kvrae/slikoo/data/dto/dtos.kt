package slikoo.kvrae.slikoo.data.dto

import com.google.gson.annotations.SerializedName
import slikoo.kvrae.slikoo.data.entities.Meal
import slikoo.kvrae.slikoo.data.entities.Notification

data class MealResponse(
    @SerializedName("repasList")
    val meals: List<Meal>
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
    @SerializedName("notifications")
    val notification: List<Notification>
)