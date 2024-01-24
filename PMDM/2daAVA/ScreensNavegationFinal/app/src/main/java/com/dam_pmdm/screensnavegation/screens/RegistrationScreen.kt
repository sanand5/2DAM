package com.dam_pmdm.screensnavegation.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dam_pmdm.screensnavegation.R
import com.dam_pmdm.screensnavegation.navigation.AppScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController) {

    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row() {
                        IconButton(
                            content = {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription="ArrowBack Icon",
                                    tint = (colorResource(id = R.color.bbva)),
                                    modifier= Modifier.size(60.dp)
                                )
                            },
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                        Text(text= stringResource(id = R.string.registration_screen),color = (colorResource(id = R.color.bbva)))
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black)
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) {
        SecondBodyContent(navController, snackbarHostState)
    }
}

@Composable
fun SecondBodyContent(navController: NavController, snackbarHostState: SnackbarHostState) {

    val radioOptions = listOf("Savings Account", "Checking Account", "Investment Account", "Business Account")
    var selectedRadioButton by remember { mutableStateOf<String?>(null) }

    val context = LocalContext.current
    val toast = stringResource(id = R.string.toast_text)
    val scope = rememberCoroutineScope()
    val snackbar = stringResource(id = R.string.snackbar_text)

    val userState = remember { mutableStateOf(TextFieldValue("")) }
    val emailState = remember { mutableStateOf(TextFieldValue("")) }
    val passwordState = remember { mutableStateOf(TextFieldValue("")) }

    val Width = 200.dp
    val Height = 60.dp
    val Padding = 8.dp

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        UserTextField(userState)
        EmailTextField(emailState)
        PasswordTextField(passwordState)

        radioOptions.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .selectable(
                        selected = selectedRadioButton == option,
                        onClick = {
                            selectedRadioButton = option
                        }
                    )
                    .width(Width)
                    .height(Height)
            ) {
                RadioButton(
                    selected = selectedRadioButton == option,
                    onClick = {
                        selectedRadioButton = option
                    },
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }

        Button(onClick = {
            if (userState.value.text.isEmpty() || emailState.value.text.isEmpty() || passwordState.value.text.isEmpty()) {
                Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
            }
            else if (selectedRadioButton.isNullOrEmpty()) {
                scope.launch {
                    snackbarHostState.showSnackbar(message = snackbar)
                }
            }
            else {
                navController.navigate(
                    route = "${AppScreen.screen3.route}/${userState.value.text}" +
                            "/${emailState.value.text}/${passwordState.value.text}" +
                            "/${selectedRadioButton}"
                )
            }
        },
            modifier = Modifier
                .width(Width)
                .height(Height)
                .padding(Padding)) {
            Text(text= stringResource(id = R.string.save),color = Color.White,)
        }

        Button(onClick = {
            navController.navigate(route = AppScreen.screen4.route)
        },
            modifier = Modifier
                .width(Width)
                .height(Height)
                .padding(Padding)) {
            Text(text = stringResource(id = R.string.exit), color = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserTextField(userState: MutableState<TextFieldValue>) {

    OutlinedTextField(
        value = userState.value,
        onValueChange = {
            userState.value = it
        },
        label = { Text(text= stringResource(id = R.string.username)) },
        placeholder = { Text(text= stringResource(id = R.string.enter_username)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "Username Icon",
                modifier = Modifier.padding(8.dp)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(emailState: MutableState<TextFieldValue>) {

    OutlinedTextField(
        value = emailState.value,
        onValueChange = {
            emailState.value = it
        },
        label = { Text(text= stringResource(id = R.string.email)) },
        placeholder = { Text(text= stringResource(id = R.string.enter_email)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "E-mail Icon",
                modifier = Modifier.padding(8.dp)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(passwordState: MutableState<TextFieldValue>) {

    OutlinedTextField(
        value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text= stringResource(id = R.string.password)) },
        placeholder = { Text(text= stringResource(id = R.string.enter_password)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Password Icon",
                modifier = Modifier.padding(8.dp)
            )
        },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Password
        ),
    )
}