package slikoo.kvrae.slikoo.data.datasources.entities

import com.google.gson.annotations.SerializedName

data class Invitation(
    val id: Int? = null,
    @SerializedName("iduserowner")
    val iduserowner: String? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("motif")
    val motif: String? = null,
    @SerializedName("userDemander")
    val userDemander: User? = null,
    @SerializedName("repas")
    val meal: Meal? = null
)
