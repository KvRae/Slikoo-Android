package slikoo.kvrae.slikoo.data.datasources.entities

import com.google.gson.annotations.SerializedName


data class UserDetails(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("idusermain")
    var idusermain: String? = "",
    @SerializedName("iduser")
    var iduser: String? = "",
    @SerializedName("fumeur")
    var fumeur: String? = "",
    @SerializedName("alcohol")
    var alcohol: String? = "",
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
    var Facebooklink: String? = "",
    @SerializedName("InstagramLink")
    var InstagramLink: String? = "",
    @SerializedName("TwitterLink")
    var TwitterLink: String? = "",
    @SerializedName("LinkedinLink")
    var LinkedinLink: String? = ""

)
