package persistence.entitys.user

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setUser (user: User)

    @Update
    suspend fun updateUser (user: User)

    @Query("SELECT Count(*) FROM user WHERE username = :username")
    suspend fun isEmpty(username: String): Int

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUser(username: String): User
}