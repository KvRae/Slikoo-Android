package slikoo.kvrae.slikoo.data.datasources.entities

import com.google.gson.annotations.SerializedName

data class Reservation(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("iduserowner")
    var iduserowner: String? = null,
    @SerializedName("user")
    var user: User? = null,
    @SerializedName("repas")
    var meal: Meal? = null,
    @SerializedName("datecreation")
    var datecreation: String? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("motif")
    var motif: String? = null
)
