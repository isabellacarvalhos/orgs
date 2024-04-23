package br.com.alura.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProductsDao
import br.com.alura.orgs.databinding.ActivityProductsListBinding
import br.com.alura.orgs.ui.recyclerview.adapter.ProductsListAdapter
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset

class ProductsListActivity : AppCompatActivity(R.layout.activity_products_list) {

    private lateinit var binding: ActivityProductsListBinding
    private val dao = ProductsDao()
    private val adapter = ProductsListAdapter(this, products = dao.searchAll())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        configureRecyclerView()
        configureActionButton()


        val dateSelector = MaterialDatePicker.Builder.datePicker().build()
        dateSelector.show(supportFragmentManager, "MATERIAL_DATE_PICKER")

        dateSelector.addOnPositiveButtonClickListener { dataInMiliseconds ->
            Log.i("MaterialDatePicker", "data em milisegundos: $dataInMiliseconds")

            val date = Instant.ofEpochMilli(dataInMiliseconds)
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .withZoneSameInstant(ZoneId.ofOffset("UTC", ZoneOffset.UTC))
                .toLocalDate()
            Log.i("MaterialDatePicker", "data com LocalDate: $date")

        }
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.searchAll())
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