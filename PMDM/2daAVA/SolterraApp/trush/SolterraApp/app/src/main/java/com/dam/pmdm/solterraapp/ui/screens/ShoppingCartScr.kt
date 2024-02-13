package com.dam.pmdm.solterraapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.navigation.AppScreen
import com.dam.pmdm.solterraapp.ui.tools.NavigationUI
import com.dam.pmdm.solterraapp.ui.tools.NormalButton
import com.dam.pmdm.solterraapp.ui.tools.showToast
import com.dam.pmdm.solterraapp.ui.viewmodel.ProductViewModel
import com.dam.pmdm.solterraapp.ui.viewmodel.listaProductos

@Composable
fun ShoppingCartScr(
    navController: NavController
) {
    NavigationUI(
        navController = navController,
        text = stringResource(id = R.string.scr_shopping),
        content = { ShoppingCartBodyContent(navController) },
        fab = { FAB(navController) }
    )
}

//TODO: fotos
@Composable
fun ShoppingCartBodyContent(navController: NavController) {
    val productViewModel = ProductViewModel()
    val productImages = listOf(
        R.drawable.ps_100,
        R.drawable.ps_150,
        R.drawable.ps_200,
        R.drawable.b_12,
        R.drawable.b_24,
        R.drawable.b_p_12,
        R.drawable.i_3000,
        R.drawable.i_5000,
    )
    val productNames = listOf(
        "Solar Panel 100W",
        "Solar Panel 150W",
        "Solar Panel 200W",
        "Lithium Battery 12V 50Ah",
        "Lithium Battery 24V 100Ah",
        "Lead-Acid Battery 12V 80Ah",
        "Solar Inverter 3000W",
        "Solar Inverter 5000W",
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
    )
    val loc = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.patron_solar),
            contentDescription = stringResource(id = R.string.img_patronSolar),
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
                for (index in productImages.indices step 2) {
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
            item{
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            color = colorResource(id = R.color.solterraRed).copy(0.8f),
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(10.dp)
                    
                ) {
                    Text(
                        text = stringResource(id = R.string.txt_yourProducts),
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        modifier = Modifier.padding(horizontal = 12.dp),
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    if(listaProductos.isEmpty()){
                        Text(
                            text = stringResource(id = R.string.txt_anyProduct),
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            modifier = Modifier.padding(horizontal = 25.dp, vertical = 4.dp),
                            color = Color.White
                            )
                    }
                    for (index in listaProductos) {
                        Row {
                            Text(
                                text = index.getName() + ": " + index.getQuantity() +", "+ index.getCategory(),
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif,
                                modifier = Modifier.padding(horizontal = 35.dp, vertical = 4.dp),
                                color = Color.White
                            )
                        }
                    }
                }
            }
            item {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    NormalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (listaProductos.isNotEmpty()) {
                                showDialog = true
                            }
                        },
                        enabled = listaProductos.isNotEmpty()
                    ) {
                        if (listaProductos.isNotEmpty()) {
                            Text(
                                text = stringResource(
                                    id = R.string.btn_buyProducts,
                                    listaProductos.size
                                )
                            )
                        } else {
                            Text(text = stringResource(id = R.string.btn_products))
                        }
                    }
                    Spacer(modifier = Modifier.fillMaxWidth())
                }
            }
            item {
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(
                    text = stringResource(id = R.string.altD_confirmTitle),
                    color = Color.White
                )
            },
            text = {
                Text(
                    text = stringResource(id = R.string.altD_confirmText),
                    color = Color.White
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                        for (product in listaProductos) {
                            productViewModel.addProduct(
                                product.getCategory(),
                                product.getName(),
                                product.getQuantity()
                            )
                        }
                        listaProductos.clear()
                        showToast(loc.getString(R.string.tst_i_buyConfirmed), loc)
                        navController.navigate(AppScreen.ExitScr.route)
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = colorResource(id = R.color.solterraRedOscuro))
                ) {
                    Text(stringResource(id = R.string.altD_yes))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showToast(loc.getString(R.string.tst_i_buyCancel), loc)
                        showDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = colorResource(id = R.color.solterraRedOscuro))
                ) {
                    Text(stringResource(id = R.string.altD_no))
                }
            },
            containerColor = colorResource(id = R.color.solterraRedClaro)
        )
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
            contentDescription = stringResource(id = R.string.img_product),
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
                contentDescription = stringResource(id = R.string.btn_add),
            )
        }
    }
}