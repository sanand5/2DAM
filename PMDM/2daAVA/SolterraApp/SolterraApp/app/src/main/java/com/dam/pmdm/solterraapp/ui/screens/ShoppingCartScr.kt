package com.dam.pmdm.solterraapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.navigation.AppScreen
import com.dam.pmdm.solterraapp.ui.utils.EmailTextField
import com.dam.pmdm.solterraapp.ui.utils.ModalNavDrawer
import com.dam.pmdm.solterraapp.ui.viewmodel.ViewModel

@Composable
fun ShoppingCartScr(
    navController: NavController
) {
    ModalNavDrawer(
        navController = navController,
        text = stringResource(id = R.string.shopping_cart)
    ){
        ShoppingCartBodyContent()
    }
}


@Composable
fun ShoppingCartBodyContent() {
    val productImages = listOf(
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        // Agrega más imágenes según sea necesario
    )
    val productNames = listOf(
        "Producto 1",
        "Producto 2",
        "Producto 3",
        // Agrega más nombres según sea necesario
    )
    val productPrices = listOf(
        "$10.99",
        "$24.99",
        "$14.99",
        // Agrega más precios según sea necesario
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(80.dp))
        }
        items(productImages.size) { index ->
            // Calcula la posición en la lista para obtener la información del producto actual
            val position = index
            val secondPosition = position + 1

            // Verifica si el segundo elemento existe en la lista
            val hasSecondItem = secondPosition < productImages.size-1

            // Crea una fila con dos elementos
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                ProductItem(
                    imageResource = productImages[position],
                    productName = productNames[position],
                    productPrice = productPrices[position]
                )

                // Verifica si hay un segundo elemento en la fila
                if (hasSecondItem) {
                    Spacer(modifier = Modifier.width(16.dp))

                    ProductItem(
                        imageResource = productImages[secondPosition],
                        productName = productNames[secondPosition],
                        productPrice = productPrices[secondPosition]
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(imageResource: Int, productName: String, productPrice: String) {
    Column {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier.width(50.dp)
        )
        Text(text = productName)
        Text(text = productPrice)
    }
}