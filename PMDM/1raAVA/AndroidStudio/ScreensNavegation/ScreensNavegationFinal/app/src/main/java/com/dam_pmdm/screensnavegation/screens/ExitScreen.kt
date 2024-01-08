package com.dam_pmdm.screensnavegation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam_pmdm.screensnavegation.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExitScreen(navController: NavController) {

    Scaffold (
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
                        Text(text= stringResource(id = R.string.exit_screen),color = (colorResource(id = R.color.bbva)))
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black)
            )
        }
    ){
        ExitBodyContent()
    }
}

@Composable
fun ExitBodyContent() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            text = stringResource(id = R.string.see_you_soon),
            fontSize = 70.sp,
            color = (colorResource(id = R.color.bbva))
        )

        Image(painter = painterResource(id = R.drawable.bbva),
            contentDescription = "BBVA module image",
            modifier = Modifier
                .widthIn(min = 0.dp, max = 600.dp))
    }
}