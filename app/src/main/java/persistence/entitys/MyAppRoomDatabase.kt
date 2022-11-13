package persistence.entitys

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.MyApplication
import persistence.entitys.product.Product
import persistence.entitys.product.ProductDAO
import persistence.entitys.user.User
import persistence.entitys.user.UserDAO

@Database(entities = [Product::class, User::class], version = 1)
abstract class MyAppRoomDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDAO
    abstract fun userDao(): UserDAO

    companion object{
        val roomDatabase:MyAppRoomDatabase = Room.
        databaseBuilder(MyApplication.myApplicationContext, MyAppRoomDatabase::class.java,
            "com.tecnoMoviles.techKings.database")
            .build()
    }

}