package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProductsDao
import br.com.alura.orgs.databinding.ActivityProductFormBinding
import br.com.alura.orgs.model.Products
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private lateinit var binding: ActivityProductFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureSaveButton()
        binding.productFormImage.setOnClickListener{
            AlertDialog.Builder(this)
                .setView(R.layout.image_form)
                .setPositiveButton("Confirmar") {_,_ ->

                }
                .setNegativeButton("Cancelar") {_,_ ->

                }
                .show()
        }
    }

    private fun configureSaveButton() {
        val button = binding.button
        val dao = ProductsDao()
        button.setOnClickListener {
            val createdProduct = createProduct()
            dao.add(createdProduct)
            finish()
        }
    }

    private fun createProduct(): Products {
        val name = binding.productFormName.text.toString()
        val description = binding.productFormDescription.text.toString()
        val priceString = binding.productFormPrice.text.toString()
        val price = priceString.toDoubleOrNull() ?: 0.0

        return Products(
            name = name,
            description = description,
            price = price
        )
    }
}
