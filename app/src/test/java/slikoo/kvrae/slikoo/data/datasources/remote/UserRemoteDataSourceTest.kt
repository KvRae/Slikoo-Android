package slikoo.kvrae.slikoo.data.datasources.remote

import org.junit.Test
import slikoo.kvrae.slikoo.data.datasources.entities.User

class UserRemoteDataSourceTest {
    val userRemoteDataSource = UserRemoteDataSource()
    val user = User(email = "hamzabenmahmoud9898@gmail.com", password = "12345678")


    @Test
    fun login(){
    }

    @Test
    fun getUserByEmail() {


    }

    @Test
    fun getNotificationsByEmail() {
    }

    @Test
    fun addRib() {
    }
}