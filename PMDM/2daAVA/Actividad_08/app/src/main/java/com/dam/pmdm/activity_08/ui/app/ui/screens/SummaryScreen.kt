package com.dam.pmdm.activity_08.ui.app.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.pmdm.activity_08.R
import com.dam.pmdm.activity_08.ui.app.ui.navigation.AppScreens
import com.dam.pmdm.activity_08.ui.app.ui.view_model.InvestedButton
import com.dam.pmdm.activity_08.ui.app.ui.view_model.NormalButton
import com.dam.pmdm.activity_08.ui.app.ui.view_model.ScaffoldGeneral
import com.dam.pmdm.activity_08.ui.app.ui.view_model.TextSummary


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SummaryScreen(
    navController: NavController,
    bread: String?,
    toping: String?,
    free: String?,
    userName: String?,
    userEmail: String?
) {
    ScaffoldGeneral(
        textReference = R.string.RegistrationScreen,
        navController = navController
    ) { SummaryBodyContent(navController, bread, toping, free, userName, userEmail) }
}

@Composable
fun SummaryBodyContent(
    navController: NavController,
    bread: String?,
    toping: String?,
    free: String?,
    userName: String?,
    userEmail: String?
) {
    Column {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = stringResource(id = R.string.myOrder),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        TextSummary(text = userName.toString(), icon = Icons.Default.Person)
        TextSummary(text = userEmail.toString(), icon = Icons.Default.Email)
        TextSummary(
            text = bread.toString(),
            icon = ImageVector.vectorResource(id = R.drawable.breadico)
        )
        TextSummary(
            text = toping.toString(),
            icon = ImageVector.vectorResource(id = R.drawable.condimentsico)
        )
        TextSummary(
            text = if (free.toString() == "true") stringResource(id = R.string.yes) else stringResource(
                id = R.string.no
            ),
            icon = ImageVector.vectorResource(id = R.drawable.glutenico)
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NormalButton(
            textReference = R.string.newOrder,
            modifier = Modifier
                .height(40.dp)
                .width(200.dp),
            onClick = { navController.navigate(route = AppScreens.RegistrationScreen.route) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        InvestedButton(
            textReference = R.string.ConfirmExit,
            modifier = Modifier
                .height(40.dp)
                .width(200.dp),
            onClick = { navController.navigate(route = AppScreens.ExitScreen.route) }
        )
        Spacer(modifier = Modifier.height(60.dp))
    }
}
