package com.dam_pmdm.screensnavegation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam_pmdm.screensnavegation.R
import com.dam_pmdm.screensnavegation.navigation.AppScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(

    navController: NavController,
    user: String,
    email: String,
    password: String,
    selectedOption: String,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row() {
                        IconButton(
                            content = {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "ArrowBack Icon",
                                    tint = (colorResource(id = R.color.bbva)),
                                    modifier = Modifier.size(60.dp)
                                )
                            },
                            onClick = {
                                navController.popBackStack()
                            }
                        )
                        Text(
                            text = stringResource(id = R.string.summary_screen),
                            color = (colorResource(id = R.color.bbva))
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black)
            )
        }
    ) {
        SummaryBodyContent(navController, user, email, password, selectedOption)
    }
}

@Composable
fun SummaryBodyContent(

    navController: NavController,
    user: String,
    email: String,
    password: String,
    selectedOption: String,
) {
    val buttonWidth = 240.dp
    val buttonHeight = 80.dp
    val buttonPadding = 12.dp
    val iconSize = 72.dp
    val textSize = 20.sp

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.data_message),
                fontSize = 25.sp,
                color = (colorResource(id = R.color.white))
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = "AccountCircle Icon",
                tint = (colorResource(id = R.color.bbva)),
                modifier = Modifier.size(iconSize)
            )
            Text("$user", fontSize = textSize)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email Icon",
                tint = (colorResource(id = R.color.bbva)),
                modifier = Modifier.size(iconSize)
            )
            Text("$email", fontSize = textSize)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Lock Icon",
                tint = (colorResource(id = R.color.bbva)),
                modifier = Modifier.size(iconSize)
            )
            Text("$password", fontSize = textSize)
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info Icon",
                tint = (colorResource(id = R.color.bbva)),
                modifier = Modifier.size(iconSize)
            )
            Text("$selectedOption", fontSize = textSize)
        }

        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier
                .width(buttonWidth)
                .height(buttonHeight)
                .padding(buttonPadding)
        ) {
            Text(
                text = stringResource(id = R.string.new_register),
                color = Color.White,
                fontSize = textSize
            )
        }

        Button(
            onClick = {
                navController.navigate(route = AppScreen.screen4.route)
            },
            modifier = Modifier
                .width(buttonWidth)
                .height(buttonHeight)
                .padding(buttonPadding)
        ) {
            Text(
                text = stringResource(id = R.string.exit),
                color = Color.White,
                fontSize = textSize
            )
        }
    }
}
