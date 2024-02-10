package com.dam.pmdm.solterraapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.ui.utils.*
import com.dam.pmdm.solterraapp.ui.viewmodel.ViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogRegScr(
    navController: NavController
) {
    val viewModel = ViewModel()
    ModalNavDrawer(
        navController = navController,
        text = stringResource(id = R.string.LogScreen),
        content = { LogRegBodyContent(viewModel) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogRegBodyContent(viewModel: ViewModel) {
    var name by remember { mutableStateOf("") }
    var direction by remember { mutableStateOf("") }
    val emailLiveData: LiveData<String> = viewModel.email
    val passLiveData: LiveData<String> = viewModel.password
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.casa),
            contentDescription = null, //TODO: content description
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
            item{
                Text( //TODO: el texto se sobrepone
                    text = stringResource(id = R.string.slogan),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(horizontal = 45.dp, vertical = 10.dp),
                    color = colorResource(id = R.color.solterraRedOscuro)
                )
            }
            item {
                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                    },
                    label = { Text("Nombre de usuario"/*TODO: strings.xml*/) },
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 15.dp),
                    singleLine = true,
                    maxLines = 1,
                )
            }
            item {
                TextField(
                    value = direction,
                    onValueChange = {
                        direction = it
                    },
                    label = { Text("Dirección"/*TODO: strings.xml*/) },
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 15.dp),
                    singleLine = true,
                    maxLines = 1,
                )
            }
            item {
                EmailTextField(emailLiveData) {
                    viewModel.onLoginChange(
                        it,
                        viewModel.password.value.toString()
                    )
                }
            }
            item {
                PasswordTextField(passwordLiveData = passLiveData, onTextFieldChange = {
                    viewModel.onLoginChange(
                        viewModel.email.value.toString(),
                        it
                    )
                })
            }
            item {
                Row {
                    NormalButton {
                        Text(text = stringResource(id = R.string.Login))
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    InvestedButton {
                        Text(text = stringResource(id = R.string.Register))
                    }
                }

            }

        }
    }
}