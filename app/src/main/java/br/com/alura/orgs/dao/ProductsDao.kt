package br.com.alura.orgs.dao

import br.com.alura.orgs.model.Products

class ProductsDao {

    fun add(products: Products){
        newProducts.add(products)
    }

    fun searchAll (): List<Products> {
        return newProducts.toList()
    }

    companion object {
        private val newProducts = mutableListOf<Products>(
            Products(name = "Salada de frutas",
                description = "Laranja, maças e uva",
                price = "12.00")
        )
    }
}