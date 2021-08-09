package com.proway.resumogeral.utils

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.proway.resumogeral.R


/**
 * Funçao snackBar esta acessivel em qualquer activity.
 * Passar por parametro uma view e um int de string (Id da string vem la do arquivo de Xml de strings)
 * ex: R.string.app_name
 *
 */
fun AppCompatActivity.snackBar(view: View, @StringRes resId: Int) {
    // Chama a funcao de esconder o teclado antes de mostrar a snackbar
    hideKeyboard()

    // Chama a funcao de para customizar a snackbar
    setupSnackBar(view, resId, R.color.red).apply {
        // mostra a snackbar na tela
        this.show()
    }
}

/**
 * Funçao snackBar esta acessivel em qualquer activity.
 * esconde o teclado
 *
 */
fun AppCompatActivity.hideKeyboard() {
    val imm =
        window.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(window.decorView.windowToken, 0)
}

/**
 * Funçao de customizar o snackBar
 * só está acessivel dentro deste arquivo
 *
 */
private fun AppCompatActivity.setupSnackBar(
    v: View,
    @StringRes resId: Int,
    @ColorRes color: Int
): Snackbar {
    // Criamos a instancia da snackBar e fazemos algumas modificaçoes.
    // Mudamos a orientacao do texto e a cor de background.
    return Snackbar.make(v, resId, Snackbar.LENGTH_LONG).apply {
        view.setBackgroundColor(getColor(color))
        view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text).apply {
            gravity = Gravity.CENTER
            textAlignment = View.TEXT_ALIGNMENT_CENTER

        }
    }
}