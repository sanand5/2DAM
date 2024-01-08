package com.dam.pmdm.activity_08.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
            .fillMaxHeight()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        item {
            Text(
                text = stringResource(R.string.brand),
                fontSize = 40.sp,
                color = Color.Red,
                )
        }
        item { 
            Text(text = stringResource(id = R.string.slogan),
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Black
            )
        }
        item { Spacer(modifier = Modifier.height(200.dp)) }
        item{
            Button(
                onClick = {
                    navController.navigate(route = AppScreens.RegistrationScreen.route+ "/t")
                },

            ) {
                Text(
                    text = stringResource(id = R.string.log_txt),
                )
            }
        }
    }
}
