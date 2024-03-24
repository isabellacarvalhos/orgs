package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityProductItemBinding
import br.com.alura.orgs.databinding.ActivityProductsListBinding
import br.com.alura.orgs.model.Products

class ProductsListAdapter(
    private val context: Context,
    products: List<Products>
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(binding: ActivityProductItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val name = binding.name
        private val description = binding.description
        private val price = binding.price
        fun binding(product: Products) {
            name.text = product.name
            description.text = product.description
            price.text = product.price
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityProductItemBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product =  products[position]
        holder.binding(product)
    }

    fun update(products: List<Products>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}
