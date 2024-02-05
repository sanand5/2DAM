package com.dam.pmdm.solterraapp.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.ui.viewmodel.ModalNavDrawer
import com.dam.pmdm.solterraapp.ui.viewmodel.scaffold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LogRegScr(navController: NavController) {
    ModalNavDrawer(
        navController = navController,
        text = stringResource(id = R.string.LogScreen)
    ){
        LogRegBodyContent()
    }
}

@Composable
fun LogRegBodyContent() {

}