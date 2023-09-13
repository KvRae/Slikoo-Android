package slikoo.kvrae.slikoo.data.repository

import slikoo.kvrae.slikoo.data.datasources.remote.UserRemoteDataSource
import slikoo.kvrae.slikoo.utils.SessionDataStore
import slikoo.kvrae.slikoo.utils.SlikooDatabase

class UserRepository(private val  session: SessionDataStore,
                     private val remoteDataSource: UserRemoteDataSource,
                     private val localDataSource: SlikooDatabase
                     ) {

    suspend fun login(email: String, password: String) {

    }

}