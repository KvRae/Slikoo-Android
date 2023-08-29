package slikoo.kvrae.slikoo.data.entities

data class NotificationL(
    val code: String,
    val foruser: List<Foruser>,
    val fromuser: List<Fromuser>,
    val id: Int,
    val idrepas: String,
    val motif: String,
    val repas: List<Meal>
)