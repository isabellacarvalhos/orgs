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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        configureRecyclerView()
        configureActionButton()
    }

    private fun configureActionButton() {
        val actionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        actionButton.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configureRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val dao = ProductsDao()
        recyclerView.adapter = ProductsListAdapter(this, products = dao.searchAll())

        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}