package persistence.entitys.user

import androidx.room.*

@Dao
interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setUser (user: User)

    @Update
    suspend fun updateUser (user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("SELECT Count(*) FROM user WHERE username = :username")
    suspend fun isEmpty(username: String): Int

    @Query("SELECT id FROM user WHERE username = :username")
    suspend fun existUsernameForEdit(username: String): Int

    @Query("SELECT * FROM user WHERE username = :username")
    suspend fun getUser(username: String): User
}