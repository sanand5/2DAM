package com.dam.pmdm.activity_10.ui.app.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.dam.pmdm.activity_10.R
import com.dam.pmdm.activity_10.ui.app.ui.navigation.AppScreens
import com.dam.pmdm.activity_10.ui.app.ui.view_model.EmailTextField
import com.dam.pmdm.activity_10.ui.app.ui.view_model.ForgotPassword
import com.dam.pmdm.activity_10.ui.app.ui.view_model.InvestedButton
import com.dam.pmdm.activity_10.ui.app.ui.view_model.NormalButton
import com.dam.pmdm.activity_10.ui.app.ui.view_model.PasswordTextField
import com.dam.pmdm.activity_10.ui.app.ui.view_model.Register
import com.dam.pmdm.activity_10.ui.app.ui.view_model.ScaffoldGeneral
import com.dam.pmdm.activity_10.ui.app.ui.view_model.ViewModel
import com.dam.pmdm.activity_10.ui.app.ui.view_model.signInWithFireBase
import com.dam.pmdm.activity_10.ui.theme.naranjaClaroSinOpacidad
import com.dam.pmdm.activity_10.ui.theme.naranjaOscuro
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegistrationScreen(navController: NavController) {
    val snackbarHostState by remember { mutableStateOf(SnackbarHostState()) }
    val viewModel = ViewModel()
    ScaffoldGeneral(
        textReference = R.string.mainScreen,
        navController = navController,
        snackbarHostState = snackbarHostState
    ) { RegistrationBodyContent(navController, viewModel) }
}

@Composable
fun RegistrationBodyContent(
    navController: NavController,
    viewModel: ViewModel
) {

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
    var showDialog by remember { mutableStateOf(false) }
    val onConfirmed: () -> Unit = {
        navController.navigate(route = AppScreens.ExitScreen.route)
    }
    val emailLiveData: LiveData<String> = viewModel.email
    val nameLiveData: LiveData<String> = viewModel.password
    val loginEnable: Boolean by viewModel.loginEnable.observeAsState(initial = false)
    var loc = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        RadioButon(selectedRadioButtonBread, radioOptionsBread) { selectedRadioButtonBread = it }
        Text(
            text = stringResource(id = R.string.fourComponents),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 8.dp),
            color = Color.Black
        )
        RadioButon(
            selectedRadioButtonSandwich,
            radioOptionsSandwich
        ) { selectedRadioButtonSandwich = it }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = checkboxChecked,
                onCheckedChange = { checkboxChecked = it },
                colors = CheckboxDefaults.colors(checkedColor = naranjaOscuro)
            )
            Text(text = stringResource(id = R.string.gluten_free))
        }

        TextFields(
            emailLiveData = emailLiveData,
            nameLiveData = nameLiveData,
            viewModel = viewModel,
            loc = loc
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom
        ) {

            NormalButton(
                textReference = R.string.order,
                modifier = Modifier.padding(16.dp),
                onClick = {
                    if (signInWithFireBase(viewModel.email.toString(), viewModel.password.toString(), loc)){

                    coroutineScope.launch { viewModel.onLoginPressed() }
                    if (selectedRadioButtonBread != null && selectedRadioButtonSandwich != null) {
                        navController.navigate(route = AppScreens.SummaryScreen.route + "/${selectedRadioButtonBread}/${selectedRadioButtonSandwich}/${checkboxChecked}/${nameLiveData.value}/${emailLiveData.value}")
                    }

                    }
                },
                enabled = loginEnable && selectedRadioButtonBread != null && selectedRadioButtonSandwich != null
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
                TextButton(
                    onClick = {
                        showDialog = false
                        onConfirmed()
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = naranjaOscuro)
                ) {
                    Text(stringResource(id = R.string.yes))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = naranjaOscuro)
                ) {
                    Text(stringResource(id = R.string.no))
                }
            },
            containerColor = naranjaClaroSinOpacidad
        )
    }
}

@Composable
fun TextFields(
    emailLiveData: LiveData<String>,
    nameLiveData: LiveData<String>,
    viewModel: ViewModel,
    loc: Context
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
    ) {
        EmailTextField(emailLiveData) {
            viewModel.onLoginChange(
                it,
                viewModel.password.value.toString()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        PasswordTextField(passwordLiveData = nameLiveData) {
            viewModel.onLoginChange(
                viewModel.email.value.toString(),
                it
            )
        }
        Row {
            Spacer(modifier = Modifier.width(21.dp))
            Register(
                viewModel.email.value.toString(),
                viewModel.password.value.toString(),
                loc = loc
            )
            Spacer(modifier = Modifier.width(78.dp))
            ForgotPassword()
        }
    }
}

@Composable
fun RadioButon(
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
                modifier = Modifier.padding(end = 8.dp),
                colors = RadioButtonDefaults.colors(
                    selectedColor = naranjaOscuro,
                    unselectedColor = naranjaOscuro
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = option)
        }
    }
}
