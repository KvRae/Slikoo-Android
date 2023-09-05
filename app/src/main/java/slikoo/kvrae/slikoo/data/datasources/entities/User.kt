package slikoo.kvrae.slikoo.data.datasources.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "user")
data class User (
    @SerializedName("id")
    @PrimaryKey(autoGenerate = true)
    private val id : Int = 0,
    @SerializedName("userIdentifier")
    var username : String = "",
    @SerializedName("email")
    var email: String = "" ,
    @SerializedName("description")
    var about: String = "",
    @SerializedName("password")
    var password: String = "",
    @SerializedName("sexe")
    var gender: String = "",
    @SerializedName("specialite")
    var speciality: String = "",
    @SerializedName("verified")
    var isVerified : Boolean = false,
    @SerializedName("roles")
    var roles : String = "",
    @SerializedName("nom")
    var firstName : String = "",
    @SerializedName("prenom")
    var lastName : String = "",
    @SerializedName("numtel")
    var phone: String = "",
    @SerializedName("adressepostal")
    var address: String = "",
    @SerializedName("codepostal")
    var postalCode: String = "",
    @SerializedName("ville")
    var city: String = "",
    @SerializedName("pays")
    var country: String = "",
    @SerializedName("avatar")
    var avatar: String = "",
    @SerializedName("datecreation")
    var createdAt: String = "",

    var updatedAt: String = "",
    @SerializedName("avatarbanner")
    var avatarThumbnail: String = "",
    @SerializedName("cinavatar")
    var cid: String = "",
    @SerializedName("RIB")
    var rib: String = "",
    @SerializedName("Hasdetails")
    var hasDetails : Boolean = false,
    @SerializedName("diplome")
    var diploma: String = "",
    @SerializedName("description")
    var bio : String = "")

