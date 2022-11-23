package persistence.entitys.product

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product (
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null,
    val name: String,
    val type: String,
    val urlPhoto: String,
    val price: Double,
    val description: String,
    val features: String,
    var trolley: Boolean,
    var recommended: Boolean,
    var bought: Boolean
    )
