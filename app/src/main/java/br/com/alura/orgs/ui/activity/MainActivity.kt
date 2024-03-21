package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.model.Products
import br.com.alura.orgs.ui.recyclerview.adapter.ProductsListAdapter

class MainActivity : AppCompatActivity() {

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