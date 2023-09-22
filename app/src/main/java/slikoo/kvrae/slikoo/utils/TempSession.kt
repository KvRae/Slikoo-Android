package slikoo.kvrae.slikoo.utils

import slikoo.kvrae.slikoo.data.datasources.entities.User

class TempSession {
    companion object {
        var token = ""
        var email = ""
        var user = User()
    }
}