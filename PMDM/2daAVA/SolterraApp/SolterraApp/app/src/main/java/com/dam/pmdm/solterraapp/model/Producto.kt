package com.dam.pmdm.solterraapp.model

class Producto(
    private var category: String,
    private var name: String,
    private var quantity: Int
) {
    fun getCategory(): String {
        return category
    }

    fun getName(): String {
        return name
    }

    fun getQuantity(): Int {
        return quantity
    }
}
