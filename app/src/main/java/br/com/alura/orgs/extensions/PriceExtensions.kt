package br.com.alura.orgs.extensions

import java.text.NumberFormat
import java.util.Locale

fun formatToBrazilCurrency(price: Double): String {
    val currencyInstance: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return currencyInstance.format(price)
}