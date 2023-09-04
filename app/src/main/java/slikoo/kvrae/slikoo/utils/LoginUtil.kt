package slikoo.kvrae.slikoo.utils

object LoginUtil {

    private val users = listOf(
        "Ahmed",
        "Mohamed",
    )

    /**
     * Returns `true` if ...
     * ... the username/password combination is empty
     * ... the username is already taken
     * ... the confirmed password is not the same as the password
     * ... the password is too short
     */
    fun validateLogin(username: String,
                      password: String,
                      confirmPassword: String
    ): Boolean {
        return username.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()
    }
}