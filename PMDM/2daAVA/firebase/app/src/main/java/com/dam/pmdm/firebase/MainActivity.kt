package com.dam.pmdm.firebase

import android.content.Context
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dam.pmdm.firebase.ui.theme.FirebaseTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

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
    var password by remember { mutableStateOf("") }
    val loc = LocalContext.current

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

        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = {
                    signUpWithFirebase(text1, password, loc)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text("Register")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    signInWithFirebase(text1, password, loc)
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            ) {
                Text("Loging")
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



private fun signInWithFirebase(username: String, password:String, loc: Context) {
    Firebase.auth.signInWithEmailAndPassword(username,password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            val user = Firebase.auth.currentUser
            if (user != null && user.isEmailVerified) {
                showToast("Login exitoso para usuario:  $username. Cuenta verificada por correo",loc )
            } else {
                showToast("Login exitoso para usuario $username. Cuenta no verificada por correo.",loc)
                sendEmailVerification(loc)
            }
        } else {
            showToast("Error en la autenticación: ${task.exception?.message}",loc)
        }
    }
}

private fun showToast(message: String, loc: Context){
    Toast.makeText(loc, message, Toast.LENGTH_SHORT).show()
}

private fun sendEmailVerification(loc: Context) {
    val user = Firebase.auth.currentUser
    user?.sendEmailVerification()?.addOnCompleteListener { task ->
        if (task.isSuccessful) {
            showToast("Se ha enviado un correo de verificación a tu dirección de correo electrónico", loc)
        } else {
            showToast("Error al enviar el correo de verificación: ${task.exception?.message}", loc)
        }
    }
}

private fun signUpWithFirebase(username:String, password:String, context: Context) {
    Firebase.auth.createUserWithEmailAndPassword(username, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            showToast("User created : ${username}",context)
        } else {
            showToast("User could not created : ${username}",context)
        }
    }
}