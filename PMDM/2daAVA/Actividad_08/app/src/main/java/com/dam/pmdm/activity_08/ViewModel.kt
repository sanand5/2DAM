package com.dam.pmdm.activity_08

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.pmdm.activity_08.ui.theme.naranjaClaro

@Composable
fun InvestedButton(textReference: Int, modifier: Modifier, onClick: () -> Unit): Unit {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Unspecified,
            contentColor = Color.Red,
        ),
        border = BorderStroke(2.dp, Color.Red)
    ) {
        Icon(Icons.Filled.ExitToApp, contentDescription = "Salir")
        Spacer(Modifier.width(10.dp))
        Text(
            text = stringResource(id = textReference),
        )
    }
}

@Composable
fun NormalButton(textReference: Int, modifier: Modifier, onClick: () -> Unit): Unit {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White,
        ),
    ) {
        Text(
            text = stringResource(id = textReference),
        )
    }
}

@Composable
fun textSummary(text: String, icon: ImageVector): Unit {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        Spacer(modifier = Modifier.width(16.dp))
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.iconFood),
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(fontSize = 20.sp),
            color = Color.Black
        )
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scaffoldGeneral(
    textReference: Int,
    navController: NavController,
    snackbarHostState: SnackbarHostState? = null,
    content: @Composable () -> Unit
): Unit {
    Scaffold(
        snackbarHost = {
            if (snackbarHostState != null) {
                SnackbarHost(hostState = snackbarHostState) // Type mismatch. Required: SnackbarHostState
            }
        },
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = naranjaClaro),
                modifier = Modifier.clickable { navController.popBackStack() },
                title = {
                    Row {
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowLeft,
                            contentDescription = stringResource(id = R.string.KeyboardArrowLeft),
                        )
                        Text(text = stringResource(id = textReference))
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            )
        }
    ) {
        FondoAplicacion()
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTextField(
    name: String,
    icon: ImageVector,
    contentDescription: String,
    text: MutableState<String>
) {
    TextField(
        value = text.value,
        onValueChange = { nuevoTexto -> text.value = nuevoTexto },
        label = { Text(name) },
        leadingIcon = { Icon(icon, contentDescription = contentDescription) },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            containerColor = naranjaClaro
        )
    )
}

@Composable
fun FondoAplicacion() {
    val imagenFondo = painterResource(id = R.drawable.background)
    Image(
        painter = imagenFondo,
        contentDescription = "Fondo de la aplicación",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}


/*
 Button(
                onClick = {
                    navController.navigate(route = AppScreens.RegistrationScreen.route+ "/t")
                },
                modifier = Modifier
                    .height(40.dp)
                    .width(200.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White,
                ),
            ) {
                Text(
                    text = stringResource(id = R.string.log_txt),
                )
            }
 */