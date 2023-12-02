package slikoo.kvrae.slikoo.data.datasources.entities

import com.google.gson.annotations.SerializedName

data class FeedBack(
    val id: Int,
    @SerializedName("etoiles")
    var rate: Int,
    @SerializedName("commentaire")
    val comment: String,
    @SerializedName("datecreation")
    val date: String,
    @SerializedName("iduserowner")
    val providerId: String,
    @SerializedName("iduserfeedbackfor")
    val recipientId: String,
    @SerializedName("images")
    val imageProvider: List<String>
)
