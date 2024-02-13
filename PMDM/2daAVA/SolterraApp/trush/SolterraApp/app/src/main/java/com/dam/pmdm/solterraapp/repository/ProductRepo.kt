package com.dam.pmdm.solterraapp.repository

import android.util.Log
import com.dam.pmdm.solterraapp.util.FireStoreCollections
import com.google.firebase.firestore.FirebaseFirestore

class ProductRepo : ProductRepoInterface {
    private val db = FirebaseFirestore.getInstance()

    override fun addProduct(
        category: String,
        name: String,
        quantity: Int,
        email: String,
        callback: (Boolean) -> Unit
    ) {
        val product = hashMapOf(
            "category" to category,
            "name" to name,
            "quantity" to quantity,
            "email" to email
        )
        db.collection(FireStoreCollections.PRODUCT).add(product)
            .addOnSuccessListener {
                callback(true)
            }
            .addOnFailureListener { e ->
                Log.w("holaaaaaa", "Error writing document into collection", e)
                callback(false)
            }
    }
}