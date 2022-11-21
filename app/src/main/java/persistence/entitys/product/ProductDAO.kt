package persistence.entitys.product

import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ProductDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun setProduct (product: Product)

    @Update
    suspend fun updateProduct (product: Product)

    @Query("SELECT * FROM product")
    suspend fun getAllProduct(): List<Product>

    @Query("SELECT * FROM product where id = :id")
    suspend fun getProductById(id: Int): Product

    @Query("SELECT * FROM product group by type")
    suspend fun getOneType(): List<Product>

    @Query("SELECT * FROM product where type = :type")
    suspend fun getType(type: String): List<Product>

    @Query("SELECT * FROM product where trolley = :trolley order By price asc")
    suspend fun getTrolley(trolley: Boolean): List<Product>

    @Query("SELECT * FROM product where recommended = :recommended")
    suspend fun getRecommended(recommended: Boolean): List<Product>

    @Query("SELECT * FROM product where bought = :bought order By price asc")
    suspend fun getBought(bought: Boolean): List<Product>
}