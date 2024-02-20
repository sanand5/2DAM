package com.dam.pmdm.examen.logic

import androidx.compose.runtime.mutableStateOf
import com.dam.pmdm.examen.R

data class Estacion(val nombre: String, val id: Int)

val estaciones = listOf(
    Estacion("Invierno", R.drawable.autumn),
    Estacion("Verano", R.drawable.summer),
    Estacion("Otoño", R.drawable.autumn),
    Estacion("Primavera", R.drawable.spring)
)
var like = mutableStateOf(0)
var index = mutableStateOf(0)

fun sumarIndice(): Unit {
    if (index.value == 3) {
        index.value = 0
    } else {
        index.value++
    }
}

fun restarIndice(): Unit {
    if (index.value == 0) {
        index.value = 3
    } else {
        index.value--
    }
}