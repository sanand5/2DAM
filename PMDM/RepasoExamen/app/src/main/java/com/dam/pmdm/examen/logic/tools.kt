package com.dam.pmdm.examen.logic

import android.content.Context
import android.widget.Toast

fun showToast(mensaje: String, loc: Context): Unit {
    Toast.makeText(loc, mensaje, Toast.LENGTH_SHORT).show()
}