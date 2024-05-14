package com.cj.week_05.helper

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import com.cj.week_05.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

fun showAlertDialog(context: Context, title: String, contents: String){
    val alertDialog = MaterialAlertDialogBuilder(context)

    alertDialog.setIcon(ResourcesCompat.getDrawable(context.resources, R.drawable.round_warning_24, null))
    alertDialog.setTitle(title)
    alertDialog.setMessage(contents)

    alertDialog.setPositiveButton(
        context.resources.getString(R.string.TXT_OK)
    ) { dialog, _ ->
        dialog.dismiss()
    }

    alertDialog.show()
}