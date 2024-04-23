package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityProductDetailsBinding
import br.com.alura.orgs.extensions.uploadImage
import br.com.alura.orgs.model.Products
import br.com.alura.orgs.extensions.formatToBrazilCurrency


class ProductDetailsActivity: AppCompatActivity(R.layout.activity_product_details) {

    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tryToUploadProduct()
    }

    private fun tryToUploadProduct() {
        intent.getParcelableExtra<Products>(PRODUCT_KEY)?.let { uploadedProduct ->
            fillInFields(uploadedProduct)
        } ?: finish()
    }

    private fun fillInFields(uploadedProduct: Products) {
        with(binding) {
            detailsImage.uploadImage(uploadedProduct.image)
            titleDetails.text = uploadedProduct.name
            descriptionDetails.text = uploadedProduct.description
            priceDetails.text = formatToBrazilCurrency(uploadedProduct.price)

            }
    }

    companion object {
        const val PRODUCT_KEY = "product"
    }
}