package slikoo.kvrae.slikoo.data.datasources.entities


import androidx.room.Entity
import androidx.room.PrimaryKey


data class User(
    var avatarUrl: String = "https://slikoo.com/UsersProfileImgs/",
    var avatarbannerUrl: String = "https://slikoo.com/UsersBannerImgs/",
    var cinavatarUrl: String = "https://slikoo.com/UsersCINimgs/",
    var Hasdetails: Boolean = false,
    var RIB: String? = "",
    var adressepostal: String = "",
    var avatar: String = "",
    var avatarbanner: String = "",
    var cinavatar: String = "",
    var codepostal: String = "",
    var datecreation: String= "",
    var description: String= "",
    var diplome: String= "",
    var email: String= "",
    var id: Int= 0,
    var nom: String= "",
    var numtel: String= "",
    var password: String= "",
    var pays: String= "",
    var prenom: String= "",
    var roles: List<String> = listOf(),
    var sexe: String= "",
    var specialite: String= "",
    var userIdentifier: String= "",
    var verified: Boolean= false,
    var ville: String= ""
)

//@Entity(tableName = "user")
//data class UserDb (
//    val Hasdetails: Boolean,
//    val RIB: String,
//    val adressepostal: String,
//    val avatar: String,
//    val avatarbanner: String,
//    val cinavatar: String,
//    val codepostal: String,
//    val datecreation: String,
//    val description: String,
//    val diplome: String,
//    val email: String,
//    @PrimaryKey(autoGenerate = true)
//    val id: Int,
//    val nom: String,
//    val numtel: String,
//    var password: String,
//    val pays: String,
//    val prenom: String,
//    val sexe: String,
//    val specialite: String,
//    val userIdentifier: String,
//    val verified: Boolean,
//    val ville: String)
