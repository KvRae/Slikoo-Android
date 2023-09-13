package slikoo.kvrae.slikoo.data.datasources.entities

data class Notification(
    val code: String,
    val foruser: List<User>,
    val fromuser: List<User>,
    val id: Int,
    val idrepas: String,
    val motif: String,
    val repas: List<Meal>
)