package slikoo.kvrae.slikoo.data.datasources.entities

data class Notification(
    val code: String = "",
    val foruser: List<User> = listOf(),
    val fromuser: List<User> = listOf(),
    val id: Int = 0,
    val idrepas: String = "",
    val motif: String = "",
    val repas: List<Meal> = listOf(),
)