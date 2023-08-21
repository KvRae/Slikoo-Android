package slikoo.kvrae.slikoo.data.models

data class User (
    val id : Int,
    val email: String,
    val password: String,
    val gender: String,
    val speciality: String,
    val isVerified : Boolean,
    val roles : String,
    val firstName : String,
    val lastName : String,
    val phone: String,
    val address: String,
    val postalCode: String,
    val city: String,
    val country: String,
    val avatar: String,
    val createdAt: String,
    val updatedAt: String,
    val avatarThumbnail: String,
    val cid: String,
    val rib: String,
    val hasDetails : Boolean,
    val diploma: String,
    val bio : String,
    )

