package slikoo.kvrae.slikoo.data.models

import java.util.Date

data class Area(
    val id: Int,
    val name: String,
    val nbPerson : Int,
    val description: String,
    val image: String,
    val price: String,
    val place: String,
    val date : Date
)
