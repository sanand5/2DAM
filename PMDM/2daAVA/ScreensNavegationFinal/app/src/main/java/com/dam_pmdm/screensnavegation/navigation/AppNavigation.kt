package com.dam_pmdm.screensnavegation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam_pmdm.screensnavegation.screens.ExitScreen
import com.dam_pmdm.screensnavegation.screens.MainScreen
import com.dam_pmdm.screensnavegation.screens.RegistrationScreen
import com.dam_pmdm.screensnavegation.screens.SummaryScreen

@Preview(showBackground = true)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.screen1.route) {
        composable(route = AppScreen.screen1.route) {
            MainScreen(navController)
        }

        composable(route = AppScreen.screen2.route) {
            RegistrationScreen(navController)
        }

        composable(route = "${AppScreen.screen3.route}/{user}/{email}/{password}/{selectedOption}") { backStackEntry ->
            val user = backStackEntry.arguments?.getString("user") ?: ""
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val password = backStackEntry.arguments?.getString("password") ?: ""
            val selectedOption = backStackEntry.arguments?.getString("selectedOption") ?: ""

            SummaryScreen(navController, user, email, password, selectedOption)
        }

        composable(route = AppScreen.screen4.route) {
            ExitScreen(navController)
        }
    }
}
