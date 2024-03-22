package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProductsDao
import br.com.alura.orgs.ui.recyclerview.adapter.ProductsListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductsListActivity : AppCompatActivity(R.layout.activity_products_list) {

    private val dao = ProductsDao()
    private val adapter = ProductsListAdapter(this, products = dao.searchAll())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configureRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.searchAll())
        configureActionButton()
    }

    private fun configureActionButton() {
        val actionButton = findViewById<FloatingActionButton>(R.id.activity_products_list_floatingActionButton)
        actionButton.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.activity_products_list_recyclerView)

        recyclerView.adapter = adapter

        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}