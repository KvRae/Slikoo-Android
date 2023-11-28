package slikoo.kvrae.slikoo.data.datasources.entities


import androidx.room.Entity
import androidx.room.PrimaryKey


data class User(
    val Hasdetails: Boolean = false,
    var RIB: String? = "",
    var adressepostal: String = "",
    val avatar: String = "",
    val avatarUrl: String = "https://slikoo.com/UsersProfileImgs/",
    var avatarbanner: String = "",
    val avatarbannerUrl: String = "https://slikoo.com/UsersBannerImgs/",
    val cinavatar: String = "",
    var codepostal: String = "",
    val datecreation: String= "",
    var description: String= "",
    val diplome: String= "",
    val email: String= "",
    val id: Int= 0,
    var nom: String= "",
    var numtel: String= "",
    var password: String= "",
    val pays: String= "",
    var prenom: String= "",
    val roles: List<String> = listOf(),
    val sexe: String= "",
    val specialite: String= "",
    val userIdentifier: String= "",
    val verified: Boolean= false,
    val ville: String= ""
)

@Entity(tableName = "user")
data class UserDb (
    val Hasdetails: Boolean,
    val RIB: String,
    val adressepostal: String,
    val avatar: String,
    val avatarbanner: String,
    val cinavatar: String,
    val codepostal: String,
    val datecreation: String,
    val description: String,
    val diplome: String,
    val email: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nom: String,
    val numtel: String,
    var password: String,
    val pays: String,
    val prenom: String,
    //val roles: List<String>,
    val sexe: String,
    val specialite: String,
    val userIdentifier: String,
    val verified: Boolean,
    val ville: String)