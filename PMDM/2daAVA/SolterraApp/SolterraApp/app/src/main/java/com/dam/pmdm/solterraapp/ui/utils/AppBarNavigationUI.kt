package com.dam.pmdm.solterraapp.ui.utils

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material3.NavigationBarItemDefaults
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
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
                route = "LogRegScr"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.ShoppingCart,
                contentDescription = context.getString(R.string.shopping_cart),
                route = "ShoppingCartScr"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.Person,
                contentDescription = context.getString(R.string.person),
                route = "ProfileScr"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.ExitToApp,
                contentDescription = context.getString(R.string.exit),
                route = "ExitScr"
            ))
    }
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val context = LocalContext.current
    var navigationSelectedItem by remember { mutableStateOf(-1)}
    NavigationBar(
        modifier = Modifier.height(50.dp),
        containerColor = colorResource(id = R.color.solterraRed),
    ) {
        BottomNavigationItem().listOfNavItems(context).forEachIndexed{index, navigationItem ->
            NavigationBarItem(
                selected = index == navigationSelectedItem,
                onClick = {
                    navigationSelectedItem = index
                    navController.navigate(navigationItem.route)
                          },
                icon = { Icon(imageVector = navigationItem.icon, contentDescription = navigationItem.contentDescription) },
                colors = NavigationBarItemDefaults
                    .colors(
                        indicatorColor = colorResource(id = R.color.solterraRedOscuro)
                    )
            )
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
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.solterraRed)),
        modifier = Modifier
            .clickable { navController.popBackStack() },
        title = {
            Row {
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
fun topBottonScaffold(
    navController: NavController,
    text: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    content: @Composable () -> Unit,
    FAB: @Composable () -> Unit = { }
){

    Scaffold(
        topBar = {
            topAppBar(text, navController, scope, drawerState)
        },
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = { FAB() },
        content = { content() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalNavDrawer(
    navController: NavController,
    text: String,
    content: @Composable () -> Unit,
    FAB: @Composable () -> Unit = { }

){
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerSheet(scope = scope, drawerState = drawerState) },
        content = {
            topBottonScaffold(
                navController = navController,
                text = text,
                scope = scope,
                drawerState = drawerState,
                content = { content() },
                FAB = { FAB() }
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerSheet(scope: CoroutineScope, drawerState: DrawerState) {
    val context = LocalContext.current
    val items = listOf(
        Icons.Default.Home, //TODO: Solterra Icon
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

    ModalDrawerSheet (
        modifier = Modifier.width(220.dp)
    ){
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