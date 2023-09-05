package slikoo.kvrae.slikoo.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import slikoo.kvrae.slikoo.data.datasources.local.UserDao
import slikoo.kvrae.slikoo.data.datasources.entities.User


@Database(entities = [User::class], version = 1)
abstract class SlikooDatabase : RoomDatabase() {
    // DAOs
    abstract fun userDao(): UserDao

    // Database singleton instance
    companion object {
       private var instance: SlikooDatabase? = null
       fun getInstance(context: Context) : SlikooDatabase? {
              if(instance == null) {
                  synchronized(SlikooDatabase::class) {
                      instance = Room.databaseBuilder(
                          context.applicationContext,
                          SlikooDatabase::class.java,
                          "slikoo.db"
                      ).allowMainThreadQueries().build()
                  }
              }
           return instance
       }

        // destroy instance
        fun destroyInstance(){ instance = null}
    }
}