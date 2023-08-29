package slikoo.kvrae.slikoo.data.entities

data class User (
    private val id : Int = 0,
    var username : String = "",
    var email: String = "" ,
    var about: String = "",
    var password: String = "",
    val gender: String = "",
    val speciality: String = "",
    val isVerified : Boolean = false,
    val roles : String = "",
    var firstName : String = "",
    var lastName : String = "",
    var phone: String = "",
    val address: String = "",
    var postalCode: String = "",
    val city: String = "",
    val country: String = "",
    val avatar: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val avatarThumbnail: String = "",
    val cid: String = "",
    val rib: String = "",
    val hasDetails : Boolean = false,
    val diploma: String = "",
    val bio : String = "",
    )
{

}

