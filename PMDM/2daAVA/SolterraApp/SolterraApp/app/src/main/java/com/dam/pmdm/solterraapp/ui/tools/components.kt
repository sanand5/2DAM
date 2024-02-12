package com.dam.pmdm.solterraapp.ui.tools

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import com.dam.pmdm.solterraapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(emailLiveData: LiveData<String>, onTextFieldChange: (String) -> Unit) {
    val email by emailLiveData.observeAsState("")
    TextField(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 15.dp)
            .width(280.dp),
        value = email,
        placeholder = { Text(text = stringResource(id = R.string.txtf_ph_email)) },
        onValueChange = { onTextFieldChange(it) },
        label = { Text(stringResource(id = R.string.txtf_emil)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        trailingIcon = {
            Icon(
                Icons.Default.Email,
                contentDescription = stringResource(id = R.string.icon_email)
            )
        },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(passwordLiveData: LiveData<String>, onTextFieldChange: (String) -> Unit) {
    val password by passwordLiveData.observeAsState("")
    var isTextVisible by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier
            .padding(horizontal = 15.dp, vertical = 15.dp)
            .width(280.dp),
        value = password,
        onValueChange = {
            onTextFieldChange(it)
        },
        visualTransformation = if (isTextVisible) VisualTransformation.None else PasswordVisualTransformation(),
        label = { Text(stringResource(id = R.string.txt_password)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            Icon(
                painter = if (isTextVisible) painterResource(id = R.drawable.eyeinvisible) else painterResource(
                    id = R.drawable.eye
                ),
                contentDescription = stringResource(id = R.string.icon_password),
                modifier = Modifier
                    .clickable {
                        isTextVisible = !isTextVisible
                    }
                    .width(25.dp),
            )
        },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoTextfield(
    value: String,
    leadingIcon: @Composable () -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable () -> Unit = {}
) {
    TextField(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
            .width(280.dp),
        value = value,
        onValueChange = {},
        leadingIcon = { leadingIcon() },
        visualTransformation = visualTransformation,
        trailingIcon = { trailingIcon() },
        singleLine = true,
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            cursorColor = colorResource(id = R.color.solterraRed),
            selectionColors = TextSelectionColors(
                handleColor = colorResource(id = R.color.solterraRed),
                backgroundColor = colorResource(id = R.color.solterraRed).copy(alpha = 0.5f),
            ),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,

            ),
    )
}

fun showToast(mensaje: String, loc: Context) {
    Toast.makeText(loc, mensaje, Toast.LENGTH_SHORT).show()
}