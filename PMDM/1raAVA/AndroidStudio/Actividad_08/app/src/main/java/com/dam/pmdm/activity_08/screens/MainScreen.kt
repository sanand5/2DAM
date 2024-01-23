package com.dam.pmdm.activity_08.screens

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.pmdm.activity_08.R
import com.dam.pmdm.activity_08.navigation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController): Unit {
        BodyContent(navController)
}
@Composable
fun BodyContent(navController: NavController){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        item {
            Text(
                text = stringResource(R.string.brand),
                fontSize = 40.sp,
                )
        }
        item {
            Text(text = stringResource(id = R.string.slogan),
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item{
            Image(
                painter = painterResource(id = R.drawable.bocatas2),
                contentDescription = "Imagen de un Sandwich",
                modifier = Modifier
                    .size(350.dp)
                    //.clip(RoundedCornerShape(10.dp))
            )

        }
        item { Spacer(modifier = Modifier.height(70.dp)) }
         item{
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.RegistrationScreen.route+ "/t")
                },
                modifier = Modifier
                    .height(50.dp) // Altura deseada para el botón
                    .width(200.dp), // Ancho deseado para el botón
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(
                    text = stringResource(id = R.string.log_txt),
                )
            }
        }
        item { Spacer(modifier = Modifier.height(30.dp)) }
        item{
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.ExitScreen.route+ "/t")
                },
                modifier = Modifier
                    .height(35.dp) // Altura deseada para el botón
                    .width(200.dp) // Ancho deseado para el botón
                    .clip(RoundedCornerShape(8.dp)) // Establece el borde redondeado
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Text(
                    text = stringResource(id = R.string.exit_txt),
                )
            }
        }
    }
}
