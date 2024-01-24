package com.dam.pmdm.activity_08.ui.app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.dam.pmdm.activity_08.R
import com.dam.pmdm.activity_08.ui.app.ui.view_model.ScaffoldGeneral
import com.dam.pmdm.activity_08.ui.theme.naranjaOscuro

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExitScreen(navController: NavController) {
    ScaffoldGeneral(
        textReference = R.string.ExitScreen,
        navController = navController
    ) { ExitBodyContent() }
}

@Composable
fun ExitBodyContent() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Text(
                modifier = Modifier.padding(36.dp),
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.goodby_msg),
                color = Color.Red,
                fontSize = 35.sp,
                lineHeight = 45.sp
            )
            Image(
                painter = painterResource(id = R.drawable.piolin),
                contentDescription = stringResource(id = R.string.piolin_img_content),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
            Text(
                modifier = Modifier.padding(36.dp),
                text = stringResource(id = R.string.bye),
                color = naranjaOscuro,
                fontStyle = FontStyle.Italic,
                fontSize = 25.sp
            )
        }
    }
}
