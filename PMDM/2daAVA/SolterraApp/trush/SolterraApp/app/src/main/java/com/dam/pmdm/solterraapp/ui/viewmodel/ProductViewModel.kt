package com.dam.pmdm.solterraapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dam.pmdm.solterraapp.model.Producto
import com.dam.pmdm.solterraapp.repository.ProductRepo
import com.dam.pmdm.solterraapp.repository.ProductRepoInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

val listaProductos: MutableList<Producto> = mutableListOf()

fun addProduct(producto: Producto) {
    listaProductos.add(producto)
}

class ProductViewModel : ViewModel() {
    private val _addProductResult = MutableStateFlow(Result.success(false))
    private val productRepository: ProductRepoInterface = ProductRepo()
    fun addProduct(category: String, name: String, quantity: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                productRepository.addProduct(
                    category = category,
                    name = name,
                    quantity = quantity,
                    email = email,
                    callback = { isSuccess -> _addProductResult.value = Result.success(isSuccess) }
                )
            } catch (e: java.lang.Exception) {
                _addProductResult.value = Result.failure(e)
            }
        }
    }
}