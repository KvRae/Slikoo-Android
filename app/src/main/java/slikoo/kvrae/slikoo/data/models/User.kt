package slikoo.kvrae.slikoo.data.models

data class User (
    val id : Int = 0,
    var email: String = "",
    var password: String = "",
    val gender: String = "",
    val speciality: String = "",
    val isVerified : Boolean = false,
    val roles : String = "",
    var firstName : String = "",
    var lastName : String = "",
    val phone: String = "",
    val address: String = "",
    val postalCode: String = "",
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
    constructor() : this(0,"","","",   "",false,"","",   "","","","","","","","","","","","",false,"","")
}

