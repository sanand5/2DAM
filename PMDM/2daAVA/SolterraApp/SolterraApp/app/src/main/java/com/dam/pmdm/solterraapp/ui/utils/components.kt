package com.dam.pmdm.solterraapp.ui.utils

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import com.dam.pmdm.solterraapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(emailLiveData: LiveData<String>, onTextFieldChange: (String) -> Unit) {
    val email by emailLiveData.observeAsState("")

    TextField(
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(passwordLiveData: LiveData<String>, onTextFieldChange: (String) -> Unit) {
    val password by passwordLiveData.observeAsState("")
    var isTextVisible by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
        value = password,
        onValueChange = {
            onTextFieldChange(it)
            //isTextVisible = it.isEmpty()
        },
        visualTransformation = if (isTextVisible) VisualTransformation.None else PasswordVisualTransformation(),
        label = { Text(stringResource(id = R.string.password)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            Icon(
                Icons.Default.Lock, //TODO: Lock/Unlock icon
                contentDescription = stringResource(id = R.string.LockIcon),
                modifier = Modifier.clickable {
                    isTextVisible = !isTextVisible
                }
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

@Composable
fun NormalButton(
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.solterraRed),
            contentColor = Color.White,
        ),
        enabled = enabled
    ) {
        content()
    }
}

@Composable
fun InvestedButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Unspecified,
            contentColor = colorResource(id = R.color.white),
        ),
        border = BorderStroke(2.dp, colorResource(id = R.color.white))
    ) {
        content()
    }
}

@Composable
fun TextSummary(text: String, icon: ImageVector, iconReference: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = iconReference,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(fontSize = 20.sp),
            color = Color.Black
        )
    }
}