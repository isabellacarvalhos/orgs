package br.com.alura.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityProductItemBinding
import br.com.alura.orgs.extensions.formatToBrazilCurrency
import br.com.alura.orgs.extensions.uploadImage
import br.com.alura.orgs.model.Products
import br.com.alura.orgs.ui.activity.ProductsListActivity
import coil.load
import java.text.NumberFormat
import java.util.Locale

class ProductsListAdapter(
    private val context: Context,
    products: List<Products> = emptyList(),
    var whenItemClicked: (product: Products) -> Unit = {},
    var whenEditClick: (product: Products) -> Unit = {},
    var whenRemoveClick: (product: Products) -> Unit = {}

    ) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    inner class ViewHolder(private val binding: ActivityProductItemBinding) :
        RecyclerView.ViewHolder(binding.root), PopupMenu.OnMenuItemClickListener {

        private lateinit var product: Products

        init {
            itemView.setOnClickListener {
                if (::product.isInitialized) {
                    whenItemClicked(product)
                }
            }
            itemView.setOnLongClickListener {
                PopupMenu(context, itemView).apply {
                    menuInflater.inflate(R.menu.menu_details_product, menu)
                    setOnMenuItemClickListener(this@ViewHolder)
                }.show()
                true
            }
        }

        private val name = binding.name
        private val description = binding.description
        private val price = binding.price
        private val image = binding.imageView

        fun binding(product: Products) {
            this.product = product
            name.text = product.name
            description.text = product.description

            val priceInCurrency: String = formatToBrazilCurrency(product.price)
            price.text = priceInCurrency

            val visibility = if (product.image != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            image.visibility = visibility

            image.uploadImage(product.image)
        }

        override fun onMenuItemClick(item: MenuItem?): Boolean {
            item?.let {
                when (it.itemId) {
                    R.id.menu_edit -> {
                        whenEditClick(product)
                    }
                    R.id.menu_remover -> {
                        whenRemoveClick(product)
                    }
                }
            }
            return true
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
