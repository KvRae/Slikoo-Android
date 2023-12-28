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
    var cherche: MutableList<String> = mutableListOf(),
    @SerializedName("algalimentaire")
    var algalimentaire: MutableList<String> = mutableListOf(),
    @SerializedName("centreinteret")
    var centreinteret: MutableList<String> = mutableListOf(),
    @SerializedName("langues")
    var langues: MutableList<String> = mutableListOf(),
    @SerializedName("chercherplus")
    var chercherplus: MutableList<String> = mutableListOf(),
    @SerializedName("Facebooklink")
    var Facebooklink: String? = "",
    @SerializedName("InstagramLink")
    var InstagramLink: String? = "",
    @SerializedName("TwitterLink")
    var TwitterLink: String? = "",
    @SerializedName("LinkedinLink")
    var LinkedinLink: String? = ""

)
