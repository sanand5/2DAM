package com.dam.pmdm.firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.pmdm.firebase.ui.theme.FirebaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirebaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyComposeFunction()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyComposeFunction() {
    var text1 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        TextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text("email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        PasswordField("password")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = {
                    // Acción para el primer botón
                    // Puedes usar text1 y text2 según tus necesidades
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text("Botón 1")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    // Acción para el segundo botón
                    // Puedes usar text1 y text2 según tus necesidades
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text("Botón 2")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyComposeFunctionPreview() {
    FirebaseTheme {
        MyComposeFunction()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(password: String) {
    var password by remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }

    // Método para actualizar el valor de la contraseña
    fun updatePassword(newPassword: String) {
        password = newPassword
    }
    TextField(
        value = password,
        onValueChange = { updatePassword(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        placeholder = { Text(text = "password") },
        visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
        maxLines = 1,
        trailingIcon = {
            IconButton(
                onClick = {
                    passwordVisibility = !passwordVisibility
                }
            ) {
                Icon(
                    imageVector = if (passwordVisibility) Icons.Filled.Face else Icons.Filled.Face,
                    contentDescription = null // Opcional: Puedes proporcionar una descripción de contenido
                )
            }
        }
    )
}