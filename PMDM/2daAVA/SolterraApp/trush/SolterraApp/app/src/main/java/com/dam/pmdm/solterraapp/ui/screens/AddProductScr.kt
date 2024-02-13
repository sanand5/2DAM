package com.dam.pmdm.solterraapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.model.Producto
import com.dam.pmdm.solterraapp.navigation.AppScreen
import com.dam.pmdm.solterraapp.ui.tools.InvestedButton
import com.dam.pmdm.solterraapp.ui.tools.NavigationUI
import com.dam.pmdm.solterraapp.ui.tools.NormalButton
import com.dam.pmdm.solterraapp.ui.tools.showToast
import com.dam.pmdm.solterraapp.ui.viewmodel.addProduct

@Composable
fun AddProductScr(navController: NavController) {
    NavigationUI(
        navController = navController,
        text = stringResource(id = R.string.scr_shopping),
        content = { AddProductBodyContent(navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AddProductBodyContent(navController: NavController) {
    var name by remember { mutableStateOf("") }

    val options = listOf(
        "-",
        stringResource(id = R.string.option_1),
        stringResource(id = R.string.option_2),
        stringResource(id = R.string.option_3),
        stringResource(id = R.string.option_4)
    )
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    val number = remember { mutableStateOf("") }

    val keyboardController = LocalSoftwareKeyboardController.current
    val loc = LocalContext.current

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.patron_solar),
            contentDescription = stringResource(id = R.string.img_patronSolar),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        LazyColumn(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //item { Spacer(modifier = Modifier.height(60.dp)) }
            item {
                Image(
                    painter = painterResource(id = R.drawable.instalacion),
                    contentDescription = stringResource(id = R.string.img_instalacion),
                    modifier = Modifier
                        .padding(30.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .width(280.dp)

                )
            }
            item {
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    }
                ) {
                    TextField(
                        readOnly = true,
                        value = selectedOptionText,
                        onValueChange = { },
                        label = { Text(stringResource(id = R.string.txtf_product)) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = expanded
                            )
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .width(280.dp),
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            focusedIndicatorColor = colorResource(id = R.color.solterraRed),
                            unfocusedIndicatorColor = colorResource(id = R.color.solterraRed),
                            disabledIndicatorColor = colorResource(id = R.color.solterraRed),
                            focusedLabelColor = colorResource(id = R.color.solterraRed),
                        ),

                        )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    options.forEach { selectionOption ->
                        DropdownMenuItem(
                            text = { Text(text = selectionOption) },
                            onClick = {
                                selectedOptionText = selectionOption
                                expanded = false
                            }
                        )
                    }
                }
            }
        }
        item{
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                label = { Text(stringResource(id = R.string.txtf_product)) },
                placeholder = { Text(text = stringResource(id = R.string.txtf_ph_productName)) },
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 15.dp)
                    .width(280.dp),
                singleLine = true,
                maxLines = 1,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = colorResource(id = R.color.solterraRed),
                    selectionColors = TextSelectionColors(
                        handleColor = colorResource(id = R.color.solterraRed),
                        backgroundColor = colorResource(id = R.color.solterraRed).copy(alpha = 0.5f)
                    ),
                    focusedIndicatorColor = colorResource(id = R.color.solterraRed),
                    unfocusedIndicatorColor = colorResource(id = R.color.solterraRed),
                    disabledIndicatorColor = colorResource(id = R.color.solterraRed),
                    focusedLabelColor = colorResource(id = R.color.solterraRed),
                )
            )
        }
        item{

            TextField(
                modifier = Modifier.width(280.dp),
                value = number.value,
                onValueChange = {
                    number.value = it
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                placeholder = { Text(text = stringResource(id = R.string.txtf_ph_number)) },

                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = colorResource(id = R.color.solterraRed),
                    selectionColors = TextSelectionColors(
                        handleColor = colorResource(id = R.color.solterraRed),
                        backgroundColor = colorResource(id = R.color.solterraRed).copy(alpha = 0.5f)
                    ),
                    focusedIndicatorColor = colorResource(id = R.color.solterraRed),
                    unfocusedIndicatorColor = colorResource(id = R.color.solterraRed),
                    disabledIndicatorColor = colorResource(id = R.color.solterraRed),
                    focusedLabelColor = colorResource(id = R.color.solterraRed),
                )
            )
        }
            item { Spacer(modifier = Modifier.height(30.dp)) }
            item {
                NormalButton(
                    modifier = Modifier.width(150.dp),
                    onClick = {
                        keyboardController?.hide()
                        try {
                            val num = number.value.toInt()

                            if (name.isNotEmpty() && !selectedOptionText.equals("-") && num >= 1) {
                                addProduct(Producto(selectedOptionText, name, num))
                                showToast(loc.getString(R.string.tst_i_addCart), loc)
                                navController.navigate(AppScreen.ShoppingCartScr.route)
                            } else {
                                showToast(loc.getString(R.string.tst_e_fieldsEmpty), loc)
                            }
                        } catch (e: NumberFormatException) {
                            showToast(loc.getString(R.string.tst_e_invalidNumber), loc)
                        }

                    }

                ) {
                    Text(text = stringResource(R.string.btn_add))
                }
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item {
                InvestedButton(
                    modifier = Modifier.width(150.dp),
                    onClick = {
                        keyboardController?.hide()
                        navController.navigate(AppScreen.ShoppingCartScr.route)
                    }
                ) {
                    Text(text = stringResource(id = R.string.btn_cancel))
                }
            }
    }
}}