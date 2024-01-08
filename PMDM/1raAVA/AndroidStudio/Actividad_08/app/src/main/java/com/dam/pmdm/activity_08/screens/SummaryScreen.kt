package com.dam.pmdm.activity_08.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.KeyboardArrowRight

import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.pmdm.activity_08.R
import com.dam.pmdm.activity_08.navigation.AppScreens
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(navController: NavController, bread: String?, toping: String?, free: String?): Unit {
    Scaffold (
        topBar = {
            TopAppBar(title = {
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.arrowIcon),
                        modifier = Modifier.clickable { navController.popBackStack() })
                    Text(text = stringResource(id = R.string.RegistrationScreen)) //TODO
                    Spacer(modifier = Modifier.width(8.dp))

                }
            })
        }){

        SummaryBodyContent(navController, bread, toping, free)
    }

}
@Composable
fun SummaryBodyContent(navController: NavController, bread: String?, toping: String?, free: String?){
    Column {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = stringResource(id = R.string.myOrder),
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        textSummary(titleText = stringResource(id = R.string.breadtype), text = bread.toString(), Icons.Default.ArrowForward)
        textSummary(titleText = stringResource(id = R.string.condiments), text = toping.toString(),Icons.Default.Add)
        textSummary(
            titleText = stringResource(id = R.string.gluten_free_summary),
            text = if (free.toString() == "true") stringResource(id = R.string.yes) else stringResource(id = R.string.no),
            Icons.Default.KeyboardArrowRight
        )
    }
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(onClick = {
            navController.navigate(route = AppScreens.RegistrationScreen.route + "/T") }) {
            Text(text = stringResource(id = R.string.newOrder))
        }
        Button(onClick = {
            navController.navigate(route = AppScreens.ExitScreen.route + "/true")}) {
            Text(text = stringResource(id = R.string.ConfirmExit))
        }
    }
}
@Composable
fun textSummary(titleText: String, text: String, icon: ImageVector): Unit {
    Text(
        text = titleText,
        style = MaterialTheme.typography.titleMedium.copy(fontSize = 35.sp),
        modifier = Modifier.padding(vertical = 8.dp)
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.iconFood)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.titleSmall.copy(fontSize = 20.sp)
        )
    }
}