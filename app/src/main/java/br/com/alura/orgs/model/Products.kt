package br.com.alura.orgs.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Products(
    @PrimaryKey(true) val id: Long = 0L,
    val name: String,
    val description: String,
    val price: Double,
    val image: String? = null
) : Parcelable

