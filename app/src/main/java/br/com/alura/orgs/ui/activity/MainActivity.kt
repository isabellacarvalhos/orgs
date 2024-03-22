package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.model.Products
import br.com.alura.orgs.ui.recyclerview.adapter.ProductsListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

        val actionButton = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        actionButton.setOnClickListener{
            val intent = Intent(this, ProductFormActivity::class.java)
            startActivity(intent)
        }
    }
}