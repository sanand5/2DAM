package com.dam.pmdm.solterraapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.ui.utils.InvestedButton
import com.dam.pmdm.solterraapp.ui.utils.NormalButton

@Composable
fun AddProductScr(): Unit {
    AddProductBodyContent()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductBodyContent(): Unit {
    var name by remember { mutableStateOf("") }

    val options = listOf("-",stringResource(id = R.string.option_1), stringResource(id = R.string.option_2), stringResource(id = R.string.option_3), stringResource(id = R.string.option_4))
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(options[0]) }

    val number = remember { mutableStateOf<Number?>(1) }
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.casa),
            contentDescription = null, //TODO: content description
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

    LazyColumn(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        item { Spacer(modifier = Modifier.height(30.dp))}
        item{
            Image(
                painter = painterResource(id = R.drawable.casa),
                contentDescription = null, //TODO: content description
                modifier = Modifier
                    .width(400.dp)
                    .clip(RoundedCornerShape(16.dp))

            )
        }
        item { Spacer(modifier = Modifier.height(30.dp))}
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
                    label = { Text("Label") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    modifier = Modifier.menuAnchor(),
                    colors = ExposedDropdownMenuDefaults.textFieldColors()
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
                label = { Text("Nombre del producto"/*TODO: strings.xml*/) },
                modifier = Modifier
                    .padding(horizontal = 15.dp, vertical = 15.dp),
                singleLine = true,
                maxLines = 1,
            )
        }
        item{

            TextField(
                value = number.value?.toString() ?: "",
                onValueChange = {
                    it.toIntOrNull()?.let { value ->
                        number.value = it.toInt()
                    }
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        item { Spacer(modifier = Modifier.height(50.dp)) }
        item {
            NormalButton (
                modifier = Modifier.width(150.dp)
            ){
                Text(text = stringResource(R.string.add))
            }
        }
        item { Spacer(modifier = Modifier.height(20.dp)) }
        item {
            InvestedButton(
                modifier = Modifier.width(150.dp)
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    }
}}