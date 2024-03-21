package br.com.alura.orgs.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.ui.recyclerview.adapter.ProductsListAdapter

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val name = findViewById<TextView>(R.id.name)
//        name.text = "Cesta de Frutas"
//        val description = findViewById<TextView>(R.id.description)
//        description.text = "Laranja, manga e morango"
//        val price = findViewById<TextView>(R.id.price)
//        price.text = "19.99"
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = ProductsListAdapter()
    }
}