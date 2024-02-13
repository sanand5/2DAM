package com.dam.pmdm.solterraapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.ui.tools.NavigationUI

@Preview
@Composable
fun Preview() {
    val navController = rememberNavController()
    ExitScr(navController)
}

@Composable
fun ExitScr(navController: NavController) {
    NavigationUI(
        navController = navController,
        text = stringResource(id = R.string.scr_exit),
        content = { ExitBodyContent() }
    )
}

@Composable
fun ExitBodyContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.casaconplacas),
            contentDescription = stringResource(id = R.string.img_casa),
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 50.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.height(25.dp))
            }
            item {
                val uriHandler = LocalUriHandler.current
                val linkUrl = stringResource(id = R.string.lnk_solterraWeb)
                Image(
                    modifier = Modifier
                        .padding(14.dp)
                        .clickable {
                            uriHandler.openUri(linkUrl)
                        },
                    painter = painterResource(id = R.drawable.lnk_solterraweb),
                    contentDescription = null
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.txt_thanksExit),
                    fontSize = 60.sp,
                    lineHeight = 55.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorResource(id = R.color.solterraRed)
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.txt_programer),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.txt_ies),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.txt_modulo),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.txt_date),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.End,
                    color = Color.White
                )
            }
            item {
                Text(
                    text = stringResource(id = R.string.txt_version),
                    modifier = Modifier.fillMaxWidth(),
                    fontSize = 10.sp,
                    textAlign = TextAlign.End,
                    color = Color.White
                )
            }

        }
    }
}