package br.com.alura.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.model.Products
import br.com.alura.orgs.ui.recyclerview.adapter.ProductsListAdapter
import java.math.BigDecimal

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ProductsListAdapter(this, products = listOf(
            Products(
                name = "teste",
                description = "desc",
                price = "19,99"
            ),
            Products(
                name = "teste 1",
                description = "desc 1",
                price = "29,99"
            )
        ))
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}