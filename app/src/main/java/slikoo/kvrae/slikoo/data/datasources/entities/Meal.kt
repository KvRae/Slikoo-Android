package slikoo.kvrae.slikoo.data.datasources.entities

data class Meal(
    //val avatarUrl: String =,
    val avatar: String = "",
    val `data`: Any = Any(),
    val date: String = "",
    val datecreation: String = "",
    var description: String = "",
    var genre: String= "",
    var genrenourriture: String="",
    val heure: String="",
    val id: Int=0,
    val iduser: String="",
    var lettre: Any = Any(),
    val localisation: String = "",
    var nbr: String = "",
    val prix: String    = "",
    var theme: String = "",
    val type: String = ""
)