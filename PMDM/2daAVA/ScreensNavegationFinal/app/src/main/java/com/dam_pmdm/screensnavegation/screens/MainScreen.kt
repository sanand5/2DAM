package com.dam_pmdm.screensnavegation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dam_pmdm.screensnavegation.R
import com.dam_pmdm.screensnavegation.navigation.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Row() {
                    IconButton(content = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home Icon",
                            tint = (colorResource(id = R.color.bbva)),
                            modifier = Modifier.size(60.dp)
                        )
                    }, onClick = {})
                    Text(
                        text = stringResource(id = R.string.main_screen),
                        color = (colorResource(id = R.color.bbva))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black)
        )
    }) {
        BodyContent(navController)
    }
}

@Composable
fun BodyContent(navController: NavController) {

    val buttonWidth = 200.dp
    val buttonHeight = 60.dp
    val buttonPadding = 8.dp

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        Image(
            painter = painterResource(id = R.drawable.bbva),
            contentDescription = "BBVA module image",
            modifier = Modifier
                .widthIn(min = 0.dp, max = 620.dp)
                .padding(buttonPadding)
        )

        Text(
            text = stringResource(id = R.string.bbva_text),
            modifier = Modifier
                .widthIn(min = 0.dp, max = 600.dp)
                .padding(buttonPadding)
        )

        Button(
            onClick = {
                navController.navigate(route = AppScreen.screen2.route)
            }, modifier = Modifier
                .width(buttonWidth)
                .height(buttonHeight)
                .padding(buttonPadding)

        ) {
            Text(
                text = stringResource(id = R.string.register), color = Color.White
            )
        }

        Button(
            onClick = {
                navController.navigate(route = AppScreen.screen4.route)
            }, modifier = Modifier
                .width(buttonWidth)
                .height(buttonHeight)
                .padding(buttonPadding)

        ) {
            Text(
                text = stringResource(id = R.string.exit), color = Color.White
            )
        }
    }
}
