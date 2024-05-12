package br.com.alura.orgs.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.alura.orgs.model.Products

@Dao
interface ProductsDao {

    @Query("SELECT * FROM Products")
    fun searchAll(): List<Products>

    @Insert
    fun save(vararg products: Products)
}