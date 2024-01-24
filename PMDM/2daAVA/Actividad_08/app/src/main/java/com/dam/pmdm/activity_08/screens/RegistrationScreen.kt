package com.dam.pmdm.activity_08.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dam.pmdm.activity_08.InvestedButton
import com.dam.pmdm.activity_08.NormalButton
import com.dam.pmdm.activity_08.R
import com.dam.pmdm.activity_08.SimpleTextField
import com.dam.pmdm.activity_08.navigation.AppScreens
import com.dam.pmdm.activity_08.scaffoldGeneral
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController): Unit {
    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    scaffoldGeneral(
        textReference = R.string.mainScreen,
        navController = navController,
        snackbarHostState = snackbarHostState
    ) { RegistrationBodyContent(navController, snackbarHostState) }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationBodyContent(navController: NavController, snackbarHostState: SnackbarHostState) {
    var selectedRadioButtonBread by remember { mutableStateOf<String?>(null) }
    var selectedRadioButtonSandwich by remember { mutableStateOf<String?>(null) }
    var checkboxChecked by remember { mutableStateOf(false) }
    val radioOptionsSandwich = listOf(
        stringResource(id = R.string.Sandwich01),
        stringResource(id = R.string.Sandwich02),
        stringResource(id = R.string.Sandwich03),
        stringResource(id = R.string.Sandwich04)
    )
    val radioOptionsBread = listOf(
        stringResource(id = R.string.bread01),
        stringResource(id = R.string.bread02),
        stringResource(id = R.string.bread03)
    )
    val context = LocalContext.current
    val userName = remember { mutableStateOf("") }
    val userEmail = remember { mutableStateOf("") }


    var showDialog by remember { mutableStateOf(false) }
    val onConfirmed: () -> Unit = {
        navController.navigate(route = AppScreens.ExitScreen.route)
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        radioButon(selectedRadioButtonBread, radioOptionsBread) { selectedRadioButtonBread = it }
        Text(
            text = stringResource(id = R.string.fourComponents),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.Black
        )
        //topings = topings()
        radioButon(
            selectedRadioButtonSandwich,
            radioOptionsSandwich
        ) { selectedRadioButtonSandwich = it }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = checkboxChecked, onCheckedChange = { checkboxChecked = it })
            Text(text = stringResource(id = R.string.gluten_free))
        }

        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally), // Alinea los elementos al centro horizontalmente
        ) {
            SimpleTextField("Name", Icons.Default.Person, "Icono de persona", userName)
            Spacer(modifier = Modifier.height(20.dp))
            SimpleTextField("Email", Icons.Default.Email, "Icono de email", userEmail)
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {
            NormalButton(
                textReference = R.string.order,
                modifier = Modifier.padding(16.dp),
                onClick = {
                    if (selectedRadioButtonBread == null && selectedRadioButtonSandwich == null) {
                        scope.launch {
                            snackbarHostState.showSnackbar("You must complete the fields")
                        }
                    } else if (selectedRadioButtonBread == null || selectedRadioButtonSandwich == null) {
                        Toast.makeText(context, R.string.CompleteField, Toast.LENGTH_SHORT).show()
                    } else {
                        navController.navigate(route = AppScreens.SummaryScreen.route + "/${selectedRadioButtonBread}/${selectedRadioButtonSandwich}/${checkboxChecked}/${userName.value}/${userEmail.value}")
                    }

                },
            )

            InvestedButton(
                R.string.exit_txt,
                modifier = Modifier.padding(16.dp),
                onClick = { showDialog = true }
            )

        }
    }
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                showDialog = false
            },
            title = {
                Text(text = stringResource(id = R.string.dialog01))
            },
            text = {
                Text(text = stringResource(id = R.string.dialog02))
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    onConfirmed()
                }) {
                    Text(stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showDialog = false
                }) {
                    Text(stringResource(id = R.string.no))
                }
            }
        )
    }

}

@Composable
fun radioButon(
    selectedRadioButton: String?,
    radioOptions: List<String>,
    onSelected: (String) -> Unit
) {
    radioOptions.forEach { option ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .selectable(
                    selected = selectedRadioButton == option,
                    onClick = {
                        onSelected(option)
                    }
                )
                .fillMaxWidth()
        ) {
            RadioButton(
                selected = selectedRadioButton == option,
                onClick = { onSelected(option) },
                modifier = Modifier.padding(end = 8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = option)
        }
    }
}
