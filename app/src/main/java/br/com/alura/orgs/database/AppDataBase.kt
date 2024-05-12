package br.com.alura.orgs.database

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.alura.orgs.database.dao.ProductsDao
import br.com.alura.orgs.model.Products

@Database(entities = [Products::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun ProductsDao() : ProductsDao
}