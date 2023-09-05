package slikoo.kvrae.slikoo.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class TokenDataStore(private val context : Context) {

    companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Slikoo")
    }

    suspend fun setUserToken(token : String){
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("user_token")] = token
        }
    }

    suspend fun getUserToken() : String{
        val dataStoreKey = stringPreferencesKey("user_token")
        val tokenFlow = context.dataStore.data.map { preferences ->
            preferences[dataStoreKey] ?: ""
        }
        return tokenFlow.first()
    }

    suspend fun clearUserToken(){
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("user_token")] = ""
        }
    }

    suspend fun setUserIsLogged(isLogged : Boolean){
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey("user_is_logged")] = isLogged.toString()
        }
    }

    suspend fun getUserIsLogged() : Boolean{
        val dataStoreKey = stringPreferencesKey("user_is_logged")
        val isLoggedFlow = context.dataStore.data.map { preferences ->
            preferences[dataStoreKey] ?: ""
        }
        return isLoggedFlow.first().toBoolean()
    }



}