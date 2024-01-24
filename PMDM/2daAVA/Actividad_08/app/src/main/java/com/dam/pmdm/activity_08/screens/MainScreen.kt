package com.dam.pmdm.activity_08.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.pmdm.activity_08.FondoAplicacion
import com.dam.pmdm.activity_08.InvestedButton
import com.dam.pmdm.activity_08.NormalButton
import com.dam.pmdm.activity_08.R
import com.dam.pmdm.activity_08.navigation.AppScreens


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController): Unit {
    FondoAplicacion()
    BodyContent(navController)
}

@Composable
fun BodyContent(navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                text = stringResource(R.string.brand),
                fontSize = 40.sp,
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        }
        item {
            Text(
                text = stringResource(id = R.string.slogan),
                fontSize = 30.sp,
                fontStyle = FontStyle.Italic,
                color = Color.Black

            )
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            Image(
                painter = painterResource(id = R.drawable.bocatas2),
                contentDescription = "Imagen de un Sandwich", //TODO: strings.xml
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )

        }
        item { Spacer(modifier = Modifier.height(70.dp)) }
        item {
            NormalButton(
                textReference = R.string.log_txt,
                modifier = Modifier
                    .height(40.dp)
                    .width(200.dp),
                onClick = { navController.navigate(route = AppScreens.RegistrationScreen.route) }
            )
        }
        item { Spacer(modifier = Modifier.height(30.dp)) }
        item {
            InvestedButton(
                R.string.exit_txt,
                Modifier
                    .height(40.dp)
                    .width(200.dp),
                { navController.navigate(route = AppScreens.ExitScreen.route) }
            )
        }
    }
}



