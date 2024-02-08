package com.dam.pmdm.solterraapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.navigation.AppScreen
import com.dam.pmdm.solterraapp.ui.utils.EmailTextField
import com.dam.pmdm.solterraapp.ui.utils.InvestedButton
import com.dam.pmdm.solterraapp.ui.utils.ModalNavDrawer
import com.dam.pmdm.solterraapp.ui.utils.NormalButton
import com.dam.pmdm.solterraapp.ui.viewmodel.ViewModel

@Composable
fun ShoppingCartScr(
    navController: NavController
) {
    ModalNavDrawer(
        navController = navController,
        text = stringResource(id = R.string.shopping_cart),
        content = { ShoppingCartBodyContent() },
        FAB = { FAB(navController) }
    )
}

//TODO: fotos
@Composable
fun ShoppingCartBodyContent() {
    val productImages = listOf(
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
        R.drawable.casa,
    )
    val productNames = listOf(
        "Panel Solar 100W",
        "Panel Solar 150W",
        "Panel Solar 200W",
        "Batería de Litio 12V 50Ah",
        "Batería de Litio 24V 100Ah",
        "Batería de Plomo-Ácido 12V 80Ah",
        "Inversor Solar 3000W",
        "Inversor Solar 5000W",
        "Regulador de Carga 20A",
        "Panel Solar 250W",
        "Batería de Litio 48V 200Ah",
        "Inversor Solar Híbrido 6000W",
        "Regulador de Carga 30A",
        "Panel Solar Plegable 120W",
        "Batería de Plomo-Ácido 24V 150Ah",
        "Inversor Solar 8000W",
        "Batería Portátil Solar 50000mAh",
        "Regulador de Carga MPPT 40A",
        "Panel Solar Flexible 100W",
    )
    val productPrices = listOf(
        "$199.99",
        "$249.99",
        "$299.99",
        "$149.99",
        "$349.99",
        "$129.99",
        "$899.99",
        "$1299.99",
        "$79.99",
        "$349.99",
        "$799.99",
        "$1599.99",
        "$99.99",
        "$349.99",
        "$549.99",
        "$1999.99",
        "$129.99",
        "$149.99",
        "$199.99",
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.patron_solar),
            contentDescription = null, //TODO: content description
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
            item {
                for (index in 0 until productImages.size step 2) {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 30.dp)
                    ) {
                        ProductItem(
                            imageResource = productImages[index],
                            productName = productNames[index],
                            productPrice = productPrices[index]
                        )
                        if (index + 1 < productImages.size) {
                            Spacer(modifier = Modifier.width(35.dp))
                            ProductItem(
                                imageResource = productImages[index + 1],
                                productName = productNames[index + 1],
                                productPrice = productPrices[index + 1]
                            )
                        }
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    InvestedButton {
                        Text(text = stringResource(id = R.string.cancel))
                    }
                    Spacer(modifier = Modifier.padding(10.dp))
                    NormalButton {
                        Text(text = "2 productos") //TODO: strings.xml
                    }
                    Spacer(modifier = Modifier.fillMaxWidth())
                }
            }
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun ProductItem(imageResource: Int, productName: String, productPrice: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(130.dp)

    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null, //TODO: content description
            modifier = Modifier
                .width(125.dp)
                .clip(RoundedCornerShape(8.dp))

        )
        Text(
            text = productName,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.white)
        )
        Text(
            text = productPrice,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = colorResource(id = R.color.red)

        )
    }
}

@Composable
fun FAB(navController: NavController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomEnd)
            .padding(16.dp),
        color = Color.Transparent
    ) {
        FloatingActionButton(
            onClick = { navController.navigate(AppScreen.AddProductScr.route) },
            containerColor = colorResource(id = R.color.solterraRed),
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(id = R.string.add),
            )
        }
    }
}