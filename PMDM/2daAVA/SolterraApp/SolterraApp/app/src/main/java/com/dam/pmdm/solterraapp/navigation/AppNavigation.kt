package com.dam.pmdm.solterraapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam.pmdm.solterraapp.ui.screens.LogRegScr

@Composable
@Preview
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.LogRegScr.route
    ) {
        composable(
            route = AppScreen.LogRegScr.route
        ) {
            LogRegScr(navController)
        }
        composable(
            route = AppScreen.ShoppingCartScr.route
        ) {
            LogRegScr(navController)
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
