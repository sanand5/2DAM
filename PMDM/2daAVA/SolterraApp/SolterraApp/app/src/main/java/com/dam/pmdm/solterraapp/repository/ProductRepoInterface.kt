package com.dam.pmdm.solterraapp.repository

interface ProductRepoInterface {
    fun addProduct(
        category: String,
        name: String,
        quantity: Int,
        email: String,
        callback: (Boolean) -> Unit
    )
}