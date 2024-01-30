package com.dam.pmdm.peliculasapp.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.pmdm.peliculasapp.ui.viewmodel.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: (FirebaseUser) -> Unit){
    val context = LocalContext.current
    val userViewModel = UserViewModel()
    var text1 by remember { mutableStateOf(TextFieldValue()) }
    var text2 by remember { mutableStateOf(TextFieldValue()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = text1,
            onValueChange = { text1 = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        TextField(
            value = text2,
            onValueChange = { text2 = it },
            trailingIcon={IconButton(
                onClick = {}
            ) {
                Icon(Icons.Default.Info, contentDescription = "Me gusta")
            }},
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )


        Button(
            onClick = {
                if (text1.text.isNotEmpty() && text1.text.isNotEmpty()){
                    userViewModel.signInWithFirebase(text1.text, text2.text, context, onLoginSuccess)
                }
            }
        ) {
            Text("Iniciar Sesion")
        }

        Button(
            onClick = {
                      userViewModel.signUp(text1.text, text2.text, context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Crear usuario")
        }
    }

}