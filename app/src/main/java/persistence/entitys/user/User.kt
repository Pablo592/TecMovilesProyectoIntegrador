package persistence.entitys.user

import android.text.Editable
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val email: String,
    val username: String,
    val name: String,
    val password: String,
    var profilePhotoUrl: String
    )
