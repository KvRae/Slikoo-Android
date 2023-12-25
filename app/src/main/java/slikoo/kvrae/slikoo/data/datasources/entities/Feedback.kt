package slikoo.kvrae.slikoo.data.datasources.entities

import com.google.gson.annotations.SerializedName

data class Feedback(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("etoiles")
    var rate: Int = 0,
    @SerializedName("commantaire")
    val comment: String = "",
    @SerializedName("datecreation")
    val date: String = "",
    @SerializedName("iduserowner")
    val providerId: String = "",
    @SerializedName("iduserfeedbackfor")
    val recipientId: String = "",
    @SerializedName("userowner")
    val provider: User = User(),
    @SerializedName("userfeedbackfor")
    val recipient: User = User(),
    @SerializedName("images")
    val imageProvider: List<String> = listOf(),
    @SerializedName("idrepas")
    val idMeal: String = ""
)
