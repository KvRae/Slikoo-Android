package slikoo.kvrae.slikoo.utils

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import slikoo.kvrae.slikoo.data.datasources.entities.UserDb
import slikoo.kvrae.slikoo.data.datasources.local.UserDao


@Database(entities = [UserDb::class], version = 1, exportSchema = false)
abstract class SlikooDatabase : RoomDatabase() {
    // DAOs
    abstract fun userDao(): UserDao
    // Database singleton instance
    companion object {
        @Volatile
        private var _INSTANCE: SlikooDatabase? = null
        // get instance
        fun getInstance(context: Context): SlikooDatabase {
            val tempInstance = _INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                return Room.databaseBuilder(
                    context.applicationContext,
                    SlikooDatabase::class.java,
                    "slikoo.db"
                ).build()
            }
        }
    }
}