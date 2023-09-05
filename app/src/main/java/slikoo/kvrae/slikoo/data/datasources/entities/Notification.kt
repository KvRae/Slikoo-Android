package slikoo.kvrae.slikoo.data.datasources.entities

import com.google.gson.annotations.SerializedName

data class Notification(
    @SerializedName("code")
    val code: String,
    @SerializedName("user")
    val receiver : List<User>,
    @SerializedName("fromuser")
    val sender : List<User>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("idrepas")
    val idMeal : String,
    @SerializedName("motif")
    val description : String,
    @SerializedName("repas")
    val meal: List<Meal>
)