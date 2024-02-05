package com.dam.pmdm.solterraapp.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.ui.screens.LogRegBodyContent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class BottomNavigationItem(
    val icon : ImageVector = Icons.Filled.Home,
    val contentDescription : String = "",
    val route : String = ""
){
    fun listOfNavItems(context: Context): List<BottomNavigationItem>{
        return listOf(
        BottomNavigationItem(
            icon = Icons.Filled.Home,
            contentDescription = context.getString(R.string.home),
            route = ""
        ),
        BottomNavigationItem(
            icon = Icons.Filled.ShoppingCart,
            contentDescription = context.getString(R.string.shopping_cart),
            route = "h"
        ),
        BottomNavigationItem(
            icon = Icons.Filled.Person,
            contentDescription = context.getString(R.string.person),
            route = "h"
        ),
        BottomNavigationItem(
            icon = Icons.Filled.ExitToApp,
            contentDescription = context.getString(R.string.exit),
            route = "h"
        ))
    }
}

@Composable
fun BottomNavigationBar(){
    val context = LocalContext.current
    var navigationSelectedItem by remember { mutableStateOf(0)}
    NavigationBar {
        BottomNavigationItem().listOfNavItems(context).forEachIndexed{index, navigationItem ->
            NavigationBarItem(
                selected = index == navigationSelectedItem,
                onClick = { navigationSelectedItem = index },
                icon = { Icon(imageVector = navigationItem.icon, contentDescription = navigationItem.contentDescription) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun topAppBar(
    text: String,
    navController: NavController,
    scope: CoroutineScope,
    drawerState: DrawerState
){
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Red),
        modifier = Modifier.clickable { navController.popBackStack() },
        title = {
            Row {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.KeyboardArrowLeft),
                )
                Text(text = text)
                Spacer(modifier = Modifier.width(8.dp))
            }
        },
        navigationIcon = {
            IconButton(onClick = { scope.launch { drawerState.open() } }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menu botones"
                )}}
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun scaffold(
    navController: NavController,
    text: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    content: @Composable () -> Unit
){

    Scaffold(
        topBar = {
            topAppBar(text,navController,scope,drawerState)
                 },
        content = { LogRegBodyContent() },
        bottomBar = { BottomNavigationBar() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalNavDrawer(
    navController: NavController,
    text: String,
    content: @Composable () -> Unit

){
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerSheet(scope = scope, drawerState = drawerState) },
        content = { scaffold(
            navController = navController,
            text = text,
            scope = scope,
            drawerState = drawerState
            ){
            content()
        }}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerSheet(scope: CoroutineScope, drawerState: DrawerState) {
    val context = LocalContext.current
    val items = listOf(
        Icons.Default.Home,
        Icons.Default.ShoppingCart,
        Icons.Default.Person,
        Icons.Default.ExitToApp
    )
    val names = listOf(
        context.getString(R.string.home),
        context.getString(R.string.shopping_cart),
        context.getString(R.string.person),
        context.getString(R.string.exit),
    )
    val selectedItem = remember { mutableStateOf(items[0]) }

    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(12.dp))
        items.forEachIndexed(){ index, item ->
            NavigationDrawerItem(
                icon = { Icon(imageVector = item, contentDescription = names[index]) },
                label = { Text(names[index]) },
                selected = item == selectedItem.value,
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                onClick = {
                    scope.launch { drawerState.close() }
                    selectedItem.value = item
                }
            )

        }
    }
}