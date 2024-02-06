package com.dam.pmdm.solterraapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.ui.utils.EmailTextField
import com.dam.pmdm.solterraapp.ui.utils.ModalNavDrawer
import com.dam.pmdm.solterraapp.ui.viewmodel.ViewModel

@Composable
@Preview
fun loginpreviw(): Unit {
    val navController = rememberNavController()
    LogRegScr(navController)
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogRegScr(
    navController: NavController
) {
    val viewModel = ViewModel()
    ModalNavDrawer(
        navController = navController,
        text = stringResource(id = R.string.LogScreen)
    ){
        LogRegBodyContent(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogRegBodyContent(viewModel: ViewModel) {
    var text by remember { mutableStateOf("") }
    val emailLiveData: LiveData<String> = viewModel.email
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ){
        item{
            Spacer(modifier = Modifier.height(70.dp))
        }
        item{
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text("Nombre de usuario"/*TODO: strings.xml*/) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
        item{
            TextField(
                value = text,
                onValueChange = {
                    text = it
                },
                label = { Text("Dirección"/*TODO: strings.xml*/) },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
        item{
            EmailTextField(emailLiveData, {})
        }

    }
}