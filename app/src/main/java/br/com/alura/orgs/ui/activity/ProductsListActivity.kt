package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.databinding.ActivityProductsListBinding
import br.com.alura.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ProductsListActivity : AppCompatActivity(R.layout.activity_products_list) {

    private lateinit var binding: ActivityProductsListBinding
    private val adapter = ProductsListAdapter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureRecyclerView()
        configureActionButton()
    }

    override fun onResume() {
        super.onResume()
        val db = AppDataBase.getInstance(this)
        val productDao = db.productsDao()
        adapter.update(productDao.searchAll())
        configureActionButton()
    }

    private fun configureActionButton() {
        val actionButton = binding.activityProductsListFloatingActionButton
        actionButton.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureRecyclerView() {
        val recyclerView = binding.activityProductsListRecyclerView
        recyclerView.adapter = adapter
        adapter.whenItemClicked = {
            val intent = Intent(
                this,
                ProductDetailsActivity::class.java
            ).apply {
                putExtra(PRODUCT_KEY, it)
            }
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    companion object {
        const val PRODUCT_KEY = "product"
    }
}