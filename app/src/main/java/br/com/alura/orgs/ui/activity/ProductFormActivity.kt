package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProductsDao
import br.com.alura.orgs.databinding.ActivityProductFormBinding
import br.com.alura.orgs.databinding.ImageFormBinding
import br.com.alura.orgs.extensions.uploadImage
import br.com.alura.orgs.model.Products
import coil.load
import java.math.BigDecimal

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private lateinit var binding: ActivityProductFormBinding
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureSaveButton()

        binding.productFormImage.setOnClickListener{
            val bindingImageForm = ImageFormBinding.inflate(layoutInflater)
            bindingImageForm.buttonForm.setOnClickListener {
                val url = bindingImageForm.imageFormUrl.text.toString()
                bindingImageForm.imageFormImageview.uploadImage(url)
            }

            AlertDialog.Builder(this)
                .setView(bindingImageForm.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    url = bindingImageForm.imageFormUrl.text.toString()
                    binding.productFormImage.uploadImage(url)
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
            price = price,
            image = url
        )
    }
}
