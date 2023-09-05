package slikoo.kvrae.slikoo.data.datasources.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import slikoo.kvrae.slikoo.data.datasources.entities.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user : User)

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUser(email : String) : User

    @Update
    fun updateUser(user : User)

    @Delete
    fun deleteUser(user : User)



}