package slikoo.kvrae.slikoo.data.datasources.entities

data class Meal(
    var avatar: String = "",
    val avatarUrl: String = "https://slikoo.com/repasImgs/",
    var `data`: Any = Any(),
    var date: String = "",
    var datecreation: String = "",
    var description: String = "",
    var genre: String= "",
    var genrenourriture: String="",
    var heure: String="",
    var id: Int=0,
    var iduser: String ="",
    var lettre: String = "",
    var localisation: String = "",
    var nbr: String = "",
    var prix: String    = "",
    var theme: String = "",
    var type: String = ""
)