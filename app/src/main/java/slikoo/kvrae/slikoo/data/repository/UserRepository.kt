package slikoo.kvrae.slikoo.data.repository

import android.content.Context
import slikoo.kvrae.slikoo.data.datasources.entities.UserDb
import slikoo.kvrae.slikoo.utils.SessionDataStore
import slikoo.kvrae.slikoo.utils.SlikooDatabase

class UserRepository(context : Context) {


    private val userLDS = SlikooDatabase.getInstance(context)?.userDao()
//    private val userRDS = UserRemoteDataSource()
    private val session = SessionDataStore(context)

    suspend fun setUserTokenAndEmail(token: String, email: String) {
        session.setUserToken(token)
        session.setUserEmail(email)
    }

    suspend fun getUserToken() : String {
        return session.getUserToken()
    }

    suspend fun getUser() : UserDb {
        return userLDS?.getUser(session.getUserEmail())!!
    }







}