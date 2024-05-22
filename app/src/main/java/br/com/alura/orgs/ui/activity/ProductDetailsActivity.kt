package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.database.dao.ProductsDao
import br.com.alura.orgs.databinding.ActivityProductDetailsBinding
import br.com.alura.orgs.extensions.uploadImage
import br.com.alura.orgs.model.Products
import br.com.alura.orgs.extensions.formatToBrazilCurrency


class ProductDetailsActivity: AppCompatActivity(R.layout.activity_product_details) {

    private lateinit var product: Products
    private lateinit var binding: ActivityProductDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tryToUploadProduct()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (::product.isInitialized) {
            val instance = AppDataBase.getInstance(this)
            when (item.itemId) {
                R.id.menu_remover -> {
                    instance.ProductsDao().remove(product)
                    finish()
                }
                R.id.menu_edit -> {

                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tryToUploadProduct() {
        intent.getParcelableExtra<Products>(PRODUCT_KEY)?.let { uploadedProduct ->
            product = uploadedProduct
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