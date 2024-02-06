package com.dam.pmdm.solterraapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam.pmdm.solterraapp.ui.screens.LogRegScr
import com.dam.pmdm.solterraapp.ui.screens.ShoppingCartScr
import com.dam.pmdm.solterraapp.ui.screens.WelcomeScr

@Composable
@Preview
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.WelcomeScr.route, //AppScreen.WelcomeScr.route
    ) {
        composable(
            route = AppScreen.WelcomeScr.route
        ) {
            WelcomeScr(navController)
        }
        composable(
            route = AppScreen.LogRegScr.route
        ) {
            LogRegScr(navController)
        }
        composable(
            route = AppScreen.ShoppingCartScr.route
        ) {
            ShoppingCartScr(navController)
        }
        composable(
            route = AppScreen.AddProductScr.route
        ) {
            LogRegScr(navController)
        }
        composable(
            route = AppScreen.ProfileScr.route
        ) {
            LogRegScr(navController)
        }
        composable(
            route = AppScreen.ExitScr.route
        ) {
            LogRegScr(navController)
        }
    }
}
