package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProductsDao
import br.com.alura.orgs.model.Products

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureSaveButton()
    }

    private fun configureSaveButton() {
        val button = findViewById<Button>(R.id.button)
        val dao = ProductsDao()
        button.setOnClickListener {
            val createdProduct = createdProduct()
            dao.add(createdProduct)
            finish()
        }
    }

    private fun createdProduct(): Products {
        val name = findViewById<EditText>(R.id.name)
        val nameProduct = name.text.toString()

        val description = findViewById<EditText>(R.id.description)
        val descriptionProduct = description.text.toString()

        val price = findViewById<EditText>(R.id.price)
        val priceProduct = price.text.toString()

        return Products(
            name = nameProduct,
            description = descriptionProduct,
            price = priceProduct
        )
    }
}
