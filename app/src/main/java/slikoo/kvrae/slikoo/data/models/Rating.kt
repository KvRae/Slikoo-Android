package slikoo.kvrae.slikoo.data.models

import java.util.Date

data class Rating(
    val id: Int,
    val rating: Int,
    val comment: String,
    val date: Date,
    val user: String
)
