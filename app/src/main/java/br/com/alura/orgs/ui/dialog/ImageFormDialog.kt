package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.ImageFormBinding
import br.com.alura.orgs.extensions.uploadImage

class ImageFormDialog(private val context: Context) {

    fun show(defaultUrl: String? = null,
             whenImageUploaded: (image: String) -> Unit
    )
    {
        ImageFormBinding
            .inflate(LayoutInflater.from(context)).apply {
                defaultUrl?.let {
                    imageFormImageview.uploadImage(it)
                    imageFormUrl.setText(it)
                }


                buttonForm.setOnClickListener {
                    val url = imageFormUrl.text.toString()
                    imageFormImageview.uploadImage(url)
                }

                AlertDialog.Builder(context)
                    .setView(root)
                    .setPositiveButton("Confirmar") { _, _ ->
                        val url = imageFormUrl.text.toString()
                        whenImageUploaded(url)
                    }
                    .setNegativeButton("Cancelar") {_,_ ->

                    }
                    .show()
            }
    }
}