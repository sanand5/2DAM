package com.dam.pmdm.solterraapp.ui.tools

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dam.pmdm.solterraapp.R
import com.dam.pmdm.solterraapp.navigation.AppScreen
import com.dam.pmdm.solterraapp.ui.viewmodel.email
import com.dam.pmdm.solterraapp.ui.viewmodel.password
import com.dam.pmdm.solterraapp.ui.viewmodel.user
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

var isNavigationEnabled by mutableStateOf(false)
data class BottomNavigationItem(
    val icon: ImageVector = Icons.Filled.Home,
    val title: Int = 0,
    val contentDescription: String = "",
    val route: String = ""
){
    fun listOfNavItems(context: Context): List<BottomNavigationItem>{
        return listOf(
            BottomNavigationItem(
                icon = Icons.Filled.Home,
                contentDescription = context.getString(R.string.nav_home),
                title = R.string.nav_home,
                route = "LogRegScr"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.ShoppingCart,
                contentDescription = context.getString(R.string.nav_shopping),
                title = R.string.nav_shopping,
                route = "ShoppingCartScr"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.Person,
                contentDescription = context.getString(R.string.nav_profile),
                title = R.string.nav_profile,
                route = "ProfileScr"
            ),
            BottomNavigationItem(
                icon = Icons.Filled.ExitToApp,
                contentDescription = context.getString(R.string.nav_exit),
                title = R.string.nav_exit,
                route = "ExitScr"
            )
        )
    }
}

var itemSelected: Int = 0

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BottomNavigationBar(navController: NavController) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    NavigationBar(
        modifier = Modifier.height(50.dp),
        containerColor = colorResource(id = R.color.solterraRed),
    ) {
        BottomNavigationItem().listOfNavItems(context).forEachIndexed { index, navigationItem ->
            NavigationBarItem(
                selected = index == itemSelected,
                onClick = {
                    navigate(keyboardController, navigationItem, navController, index)
                },
                icon = {
                    Icon(
                        imageVector = navigationItem.icon,
                        contentDescription = navigationItem.contentDescription
                    )
                },
                colors = NavigationBarItemDefaults
                    .colors(
                        unselectedIconColor = Color.White,
                        indicatorColor = colorResource(id = R.color.solterraRedOscuro)
                    )
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun TopAppBar(
    text: String,
    scope: CoroutineScope,
    drawerState: DrawerState
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.solterraRed)),
        title = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = text,
                    modifier = Modifier.weight(1f),
                    color = Color.White
                )
                if (user.isNotEmpty()) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = stringResource(id = R.string.icon_person),
                        modifier = Modifier.padding(end = 8.dp),
                        tint = Color.White
                    )
                    Text(
                        text = user,
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                scope.launch { drawerState.open() }
                keyboardController?.hide()
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = stringResource(id = R.string.icon_menu),
                    tint = Color.White
                )
            }
        }
    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBottonScaffold(
    navController: NavController,
    text: String,
    scope: CoroutineScope,
    drawerState: DrawerState,
    content: @Composable () -> Unit,
    fab: @Composable () -> Unit = { },
) {

    Scaffold(
        topBar = {
            TopAppBar(text, scope, drawerState)
        },
        bottomBar = { BottomNavigationBar(navController) },
        floatingActionButton = { fab() },
        content = { content() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationUI(
    navController: NavController,
    text: String,
    content: @Composable () -> Unit,
    fab: @Composable () -> Unit = { }

) {

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = { DrawerSheet(scope = scope, drawerState = drawerState, navController) },
        content = {
            TopBottonScaffold(
                navController = navController,
                text = text,
                scope = scope,
                drawerState = drawerState,
                content = { content() },
                fab = { fab() },
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun DrawerSheet(
    scope: CoroutineScope,
    drawerState: DrawerState,
    navController: NavController
) {
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current


    ModalDrawerSheet(
        modifier = Modifier.width(220.dp),
    ) {

        Spacer(modifier = Modifier.height(12.dp))
        BottomNavigationItem().listOfNavItems(context).forEachIndexed { index, navigationItem ->
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = navigationItem.icon,
                        contentDescription = navigationItem.contentDescription
                    )
                },
                label = { Text(stringResource(id = navigationItem.title)) },
                selected = index == itemSelected,
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                onClick = {
                    scope.launch { drawerState.close() }
                    navigate(keyboardController, navigationItem, navController, index)
                },
            )
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
fun navigate(
    keyboardController: SoftwareKeyboardController?,
    navigationItem: BottomNavigationItem,
    navController: NavController,
    index: Int
) {
    keyboardController?.hide()
    itemSelected = index
    if (isNavigationEnabled) {
        if (navigationItem.route != AppScreen.ExitScr.route && navigationItem.route != AppScreen.LogRegScr.route) {
            navController.navigate(navigationItem.route)
        } else {
            isNavigationEnabled = false
            user = ""
            email = ""
            password = ""
            navController.navigate(navigationItem.route)
        }
    } else if (navigationItem.route == AppScreen.ExitScr.route || navigationItem.route == AppScreen.LogRegScr.route) {
        navController.navigate(navigationItem.route)
    }
}