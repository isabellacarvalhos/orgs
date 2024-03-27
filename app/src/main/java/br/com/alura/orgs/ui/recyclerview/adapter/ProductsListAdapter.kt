package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.databinding.ActivityProductItemBinding
import br.com.alura.orgs.model.Products
import coil.load
import java.text.NumberFormat
import java.util.Locale

class ProductsListAdapter(
    private val context: Context,
    products: List<Products>
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder(binding: ActivityProductItemBinding): RecyclerView.ViewHolder(binding.root) {

        private val name = binding.name
        private val description = binding.description
        private val price = binding.price
        private val image = binding.imageView

        fun binding(product: Products) {
            name.text = product.name
            description.text = product.description

            val priceInCurrency: String = formatToBrazilCurrency(product.price)
            price.text = priceInCurrency

            image.load(product.image)
        }

        private fun formatToBrazilCurrency(price: Double): String {
            val currencyInstance: NumberFormat = NumberFormat
                .getCurrencyInstance(Locale("pt", "br"))
            return currencyInstance.format(price)
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
