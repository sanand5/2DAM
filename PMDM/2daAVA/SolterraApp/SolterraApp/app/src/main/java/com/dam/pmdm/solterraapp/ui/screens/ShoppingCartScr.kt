package com.dam.pmdm.solterraapp.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.navigation.AppScreen
import com.dam.pmdm.solterraapp.ui.utils.EmailTextField
import com.dam.pmdm.solterraapp.ui.utils.ModalNavDrawer
import com.dam.pmdm.solterraapp.ui.viewmodel.ViewModel

@Composable
fun ShoppingCartScr(
    navController: NavController
) {
    ModalNavDrawer(
        navController = navController,
        text = stringResource(id = R.string.shopping_cart)
    ){
        ShoppingCartBodyContent()
    }
}


@Composable
fun ShoppingCartBodyContent() {

}