package br.com.alura.orgs.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Products(
    val name: String,
    val description: String,
    val price: Double,
    val image: String? = null
) : Parcelable

