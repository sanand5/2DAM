package com.dam.pmdm.activity_08.screens

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dam.pmdm.activity_08.R
import com.dam.pmdm.activity_08.navigation.AppScreens
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(navController: NavController, paramText: String?): Unit {
    val snackbarHostState by remember { mutableStateOf ( SnackbarHostState() ) }
    Scaffold (
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(title = {
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrowIcon),
                        modifier = Modifier.clickable { navController.popBackStack() })
                    Text(text = stringResource(id = R.string.mainScreen))
                    Spacer(modifier = Modifier.width(8.dp))

                }
            })
        }){
        RegistrationBodyContent(navController, paramText, snackbarHostState)
    }
}
@Composable
fun RegistrationBodyContent(navController: NavController, paramText: String?, snackbarHostState: SnackbarHostState){
    var selectedRadioButtonBread by remember { mutableStateOf<String?>(null) }
    var selectedRadioButtonSandwich by remember { mutableStateOf<String?>(null) }
    var checkboxChecked by remember { mutableStateOf(false) }
    val radioOptionsSandwich = listOf(stringResource(id = R.string.Sandwich01), stringResource(id = R.string.Sandwich02), stringResource(id = R.string.Sandwich03), stringResource(id = R.string.Sandwich04))
    val radioOptionsBread = listOf(stringResource(id = R.string.bread01), stringResource(id = R.string.bread02), stringResource(id = R.string.bread03))
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }
    val onConfirmed: () -> Unit = {
        navController.navigate(route = AppScreens.ExitScreen.route + "/false")
    }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        Spacer(modifier = Modifier.height(40.dp))
        radioButon(selectedRadioButtonBread, radioOptionsBread) { selectedRadioButtonBread = it }
        Text(
            text = stringResource(id = R.string.fourComponents),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        //topings = topings()
        radioButon(selectedRadioButtonSandwich, radioOptionsSandwich) { selectedRadioButtonSandwich = it }
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Checkbox(checked = checkboxChecked, onCheckedChange = {checkboxChecked = it})
            Text(text = stringResource(id = R.string.gluten_free))
        }
        Spacer(modifier = Modifier.weight(1f))
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ){
            Button(
                onClick = {
                    if (selectedRadioButtonBread == null && selectedRadioButtonSandwich == null) {
                        scope.launch {
                            snackbarHostState.showSnackbar("You must complete the fields")
                        }
                    } else if (selectedRadioButtonBread == null || selectedRadioButtonSandwich == null){
                        Toast.makeText(context, R.string.CompleteField, Toast.LENGTH_SHORT).show()
                    }else{
                        navController.navigate(route = AppScreens.SummaryScreen.route + "/${selectedRadioButtonBread}/${selectedRadioButtonSandwich}/${checkboxChecked}")
                    }

                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.order))
            }

            Button(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = stringResource(id = R.string.exit_txt))
            }

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
fun radioButon(selectedRadioButton: String?, radioOptions: List<String>, onSelected: (String) -> Unit) {
    radioOptions.forEach{option ->
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .selectable(
                    selected = selectedRadioButton == option,
                    onClick = {
                        onSelected(option)
                    }
                )
                .fillMaxWidth()
        ){
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

@SuppressLint("RememberReturnType")
@Composable
fun showToast(message: String) {
    val context = LocalContext.current
    remember {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}