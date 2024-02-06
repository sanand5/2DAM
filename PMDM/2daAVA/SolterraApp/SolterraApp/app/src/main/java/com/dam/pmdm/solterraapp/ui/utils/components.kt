package com.dam.pmdm.solterraapp.ui.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.dam.pmdm.solterraapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(emailLiveData: LiveData<String>, onTextFieldChange: (String) -> Unit) {
    val email by emailLiveData.observeAsState("")

    TextField(
        modifier = Modifier.padding(
            horizontal = 20.dp
        ),
        value = email,
        onValueChange = { onTextFieldChange(it) },
        label = { Text(stringResource(id = R.string.email)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        trailingIcon = {
            Icon(
                Icons.Default.Email,
                contentDescription = stringResource(id = R.string.emailIcon)
            )
        },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
//            containerColor = naranjaClaro,
//            cursorColor = naranjaOscuro,
//            focusedIndicatorColor = naranjaOscuro,
//            focusedLabelColor = naranjaOscuro,
        )
    )
}