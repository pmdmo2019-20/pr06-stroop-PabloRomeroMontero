package es.iessaladillo.pedrojoya.stroop

import android.content.Context
import androidx.appcompat.app.AlertDialog
import es.iessaladillo.pedrojoya.stroop.R

fun show(context: Context, descripcion: String) {
    AlertDialog.Builder(context)
        .setTitle(R.string.help_title)
        .setMessage(descripcion)
        .setPositiveButton(R.string.help_accept) { _, _ -> }
        .create()
        .show()
}


