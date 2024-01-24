package com.dam.pmdm.activity_08.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dam.pmdm.activity_08.screens.ExitScreen
import com.dam.pmdm.activity_08.screens.MainScreen
import com.dam.pmdm.activity_08.screens.RegistrationScreen
import com.dam.pmdm.activity_08.screens.SummaryScreen

@Composable
@Preview
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.MainScreen.route
    ) {
        composable(
            route = AppScreens.MainScreen.route
        ) {
            MainScreen(navController)
        }
        composable(
            route = AppScreens.RegistrationScreen.route
        ) {
            RegistrationScreen(navController)
        }
        composable(
            route = AppScreens.SummaryScreen.route + "/{bread}/{topings}/{free}/{userName}/{userEmail}",
            arguments = listOf(
                navArgument(name = "bread") { type = NavType.StringType },
                navArgument(name = "topings") { type = NavType.StringType },
                navArgument(name = "free") { type = NavType.StringType },
                navArgument(name = "userName") { type = NavType.StringType },
                navArgument(name = "userEmail") { type = NavType.StringType }
            )) {
            val bread = it.arguments?.getString("bread")
            val topings = it.arguments?.getString("topings")
            val free = it.arguments?.getString("free")
            val userName = it.arguments?.getString("userName")
            val userEmail = it.arguments?.getString("userEmail")

            SummaryScreen(navController, bread, topings, free, userName, userEmail)
        }
        composable(
            route = AppScreens.ExitScreen.route
        ) {
            ExitScreen(navController)
        }
    }
}
