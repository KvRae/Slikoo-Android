package slikoo.kvrae.slikoo.data.datasources.entities

import com.google.gson.annotations.SerializedName


data class UserDetails(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("idusermain")
    val idusermain: String? = "",
    @SerializedName("iduser")
    val iduser: String? = "",
    @SerializedName("fumeur")
    val fumeur: String? = "",
    @SerializedName("alcohol")
    val alcohol: String? = "",
    @SerializedName("cherche")
    val cherche: List<String> = listOf(),
    @SerializedName("algalimentaire")
    val algalimentaire: List<String> = listOf(),
    @SerializedName("centreinteret")
    val centreinteret: List<String> = listOf(),
    @SerializedName("langues")
    val langues: List<String> = listOf(),
    @SerializedName("chercherplus")
    val chercherplus: List<String> = listOf(),
    @SerializedName("Facebooklink")
    val facebooklink: String? = "",
    @SerializedName("InstagramLink")
    val InstagramLink: String? = "",
    @SerializedName("TwitterLink")
    val TwitterLink: String? = "",
    @SerializedName("LinkedinLink")
    val LinkedinLink: String? = ""

)
