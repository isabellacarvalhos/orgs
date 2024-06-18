package br.com.alura.orgs.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.alura.orgs.database.dao.ProductsDao
import br.com.alura.orgs.model.Products

@Database(entities = [Products::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun productsDao() : ProductsDao

    companion object {
        fun getInstance(context: Context) : AppDataBase {
            return Room.databaseBuilder(
                context,
                AppDataBase::class.java,
                "orgs.db"
            ).allowMainThreadQueries()
                .build()
        }
    }
}