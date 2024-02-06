package com.dam.pmdm.solterraapp.ui.screens

import android.provider.CalendarContract
import androidx.annotation.ColorRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dam.pmdm.solterraapp.navigation.AppScreen
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import kotlinx.coroutines.delay
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource

@Composable
fun WelcomeScr(navController: NavController): Unit {
    var isLoading by remember { mutableStateOf(true) }
    WelcomeBodyContent(isLoading, navController)
    LaunchedEffect(Unit) {
        isLoading = true
        delay(3000)
        isLoading = false
    }
}

@Composable
fun WelcomeBodyContent(isLoading: Boolean, navController: NavController): Unit {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo_content_description),
            modifier = Modifier.width(250.dp)
        )
        Box(
            modifier = Modifier.width(250.dp).height(30.dp),
            contentAlignment = Alignment.Center,
            ){
            if (isLoading) {
                LinearProgressIndicator(
                    modifier = Modifier.width(250.dp),
                    color = colorResource(id = R.color.solterraRed)
                )
            }else{
                navController.navigate(route = AppScreen.LogRegScr.route)
            }
        }
        Image(
            painter = painterResource(id = R.drawable.textosolterra),
            contentDescription = stringResource(id = R.string.logo_content_description),
            modifier = Modifier.width(250.dp)

        )
    }}