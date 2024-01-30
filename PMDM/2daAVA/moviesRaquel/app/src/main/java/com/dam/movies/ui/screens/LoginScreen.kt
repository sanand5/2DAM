package com.dam.movies.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.dam.movies.R
import com.dam.movies.ui.viewmodel.UserViewModel
import com.google.firebase.auth.FirebaseUser
import com.google.rpc.context.AttributeContext.Resource

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess:(FirebaseUser)-> Unit) {
    Scaffold { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {}
        LoginBodyContent(onLoginSuccess)
    }
}

fun showToast(message: String, loc: Context) {
    Toast.makeText(loc, message, Toast.LENGTH_LONG).show()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginBodyContent(onLoginSuccess: (FirebaseUser) -> Unit) {
    val context = LocalContext.current
    val userViewModel: UserViewModel = UserViewModel()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )
        Row (
        )
        {
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        userViewModel.signInWithFirebase( username, password,context, onLoginSuccess)
                    } else {
                        showToast("Datos vacios del username ", context)
                    }
                           },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue))
            ) {
                Text(text = "Sign in")
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Button(
                onClick = {
                    if (username.isNotEmpty() && password.isNotEmpty()) {
                        userViewModel.signUp( username, password,context)
                    } else {
                        showToast("Datos vacios del password", context)
                    }
                },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue))
            ) {
                Text(text = "Sign up")
            }
        }
    }
}