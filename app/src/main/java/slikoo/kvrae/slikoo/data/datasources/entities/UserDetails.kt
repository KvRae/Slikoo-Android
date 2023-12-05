package slikoo.kvrae.slikoo.data.datasources.entities

import com.google.gson.annotations.SerializedName


data class UserDetails(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("idusermain")
    var iduser: String? = null,
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
