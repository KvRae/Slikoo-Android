package slikoo.kvrae.slikoo.data.repository

import android.content.Context
import slikoo.kvrae.slikoo.data.datasources.entities.User
import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.SlikooDatabase

class UserRepository(context: Context) {
    private val userRemoteDataSource = UserRemoteDataSource()
    private val db = SlikooDatabase.getInstance(context)


    suspend fun getUser(email : String) {
        db?.userDao()?.getUser(email)
    }

    suspend fun createUser(user: User) {
        /*userRemoteDataSource.createUser(user)
        db?.userDao()?.insertUser(user)*/

    }

    suspend fun updateUser() {

    }


}