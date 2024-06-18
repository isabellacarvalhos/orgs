package br.com.alura.orgs.ui.activity

import android.content.Intent
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
import br.com.alura.orgs.ui.activity.ProductsListActivity.Companion.PRODUCT_KEY


class ProductDetailsActivity: AppCompatActivity(R.layout.activity_product_details) {

    private var productId: Long? = null
    private var product: Products? = null
    private lateinit var binding: ActivityProductDetailsBinding
    val productDao by lazy {
        AppDataBase.getInstance(this).productsDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tryToUploadProduct()
    }

    override fun onResume() {
        super.onResume()
        productId?.let { id ->
            product = productDao.searchForId(id)
        }
        product?.let {
            fillInFields(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details_product, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_remover -> {
                product?.let { productDao.remove(it) }
                finish()
            }
            R.id.menu_edit -> {
                Intent(this, ProductFormActivity::class.java).apply {
                    putExtra(PRODUCT_KEY, product)
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tryToUploadProduct() {
        intent.getParcelableExtra<Products>(PRODUCT_KEY)?.let { uploadedProduct ->
            productId = uploadedProduct.id
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