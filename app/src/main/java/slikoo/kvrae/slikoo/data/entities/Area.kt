package slikoo.kvrae.slikoo.data.entities

import java.util.Date

data class Area(
    val id: Int,
    val name: String,
    val nbPerson : Int,
    val description: String,
    val image: String,
    val price: Double,
    val place: String,
    val date : Date
)
