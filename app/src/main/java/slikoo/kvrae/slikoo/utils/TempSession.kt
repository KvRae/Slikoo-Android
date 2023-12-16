package slikoo.kvrae.slikoo.utils

import slikoo.kvrae.slikoo.data.datasources.entities.User

class TempSession {
    companion object {
        var token = ""
        var email = ""
        var user = User(
            id = 0,
            nom = "",
            prenom = "",
            numtel = "",
            adressepostal = "",
            avatar = "",
            avatarbanner = "",
            cinavatar = "",
            avatarUrl = "",
            cinavatarUrl = "",
            avatarbannerUrl = "",
            email = "",
            password = "",
            RIB = "",
            ville = "",
            codepostal = "",
            description = "",
            pays = "",
            Hasdetails = false,
            sexe = "",
            specialite = "",
        )
        fun clear() {
            token = ""
            email = ""
            user = User(
                id = 0,
                nom = "",
                prenom = "",
                numtel = "",
                adressepostal = "",
                avatar = "",
                avatarbanner = "",
                cinavatar = "",
                avatarUrl = "",
                cinavatarUrl = "",
                avatarbannerUrl = "",
                email = "",
                password = "",
                RIB = "",
                ville = "",
                codepostal = "",
                description = "",
                pays = "",
                Hasdetails = false,
                sexe = "",
                specialite = "",
            )
        }
    }


}