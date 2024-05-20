package br.com.alura.orgs.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.database.AppDataBase
import br.com.alura.orgs.databinding.ActivityProductFormBinding
import br.com.alura.orgs.extensions.uploadImage
import br.com.alura.orgs.model.Products
import br.com.alura.orgs.ui.dialog.ImageFormDialog
import com.google.android.material.datepicker.MaterialDatePicker
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

class ProductFormActivity : AppCompatActivity(R.layout.activity_product_form) {

    private lateinit var binding: ActivityProductFormBinding
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "Cadastre Produto"
        configureSaveButton()

        binding.productFormImage.setOnClickListener {
            ImageFormDialog(this).show(url) {
                image ->
                url = image
                binding.productFormImage.uploadImage(url)
            }
        }

        binding.productFormDate.setOnClickListener {
            configureDate()
        }
    }

    private fun configureDate() {
            val dateSelector = MaterialDatePicker.Builder.datePicker().build()

            dateSelector.show(supportFragmentManager, "MATERIAL_DATE_PICKER")

            dateSelector.addOnPositiveButtonClickListener { dateInMilliseconds ->
                val localDate = Instant.ofEpochMilli(dateInMilliseconds)
                    .atZone(ZoneId.of("America/Sao_Paulo"))
                    .withZoneSameInstant(ZoneId.ofOffset("UTC", ZoneOffset.UTC))
                    .toLocalDate()

                val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
                val formattedDate = localDate.format(dateFormatter)

                binding.productFormDate.setText(formattedDate)
            }
    }

    private fun configureSaveButton() {
        val button = binding.button
        val db = AppDataBase.getInstance(this)
        val productDao = db.ProductsDao()
        button.setOnClickListener {
            val createdProduct = createProduct()
            productDao.save(createdProduct)
            finish()
        }
    }

    private fun createProduct(): Products {
        val name = binding.productFormName.text.toString()
        val description = binding.productFormDescription.text.toString()
        val priceString = binding.productFormPrice.text.toString()
        val price = priceString.toDoubleOrNull() ?: 0.0

        return Products(
            name = name,
            description = description,
            price = price,
            image = url
        )
    }
}
