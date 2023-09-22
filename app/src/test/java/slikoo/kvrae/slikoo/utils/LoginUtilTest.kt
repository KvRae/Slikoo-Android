package slikoo.kvrae.slikoo.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class LoginUtilTest {

    @Test
    fun `empty username returns false`() {
        val result = LoginUtil.validateLogin(
            username = "",
            password = "123",
            confirmPassword = "123"
        )
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password returns false`(){
        val result = LoginUtil.validateLogin(
            username = "Ahmed",
            password = "",
            confirmPassword = "")
        assertThat(result).isFalse()
    }
}

