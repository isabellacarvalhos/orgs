package br.com.alura.orgs.model

data class Products(
    val name: String,
    val description: String,
    val price: Double,
    val image: String? = null
)

