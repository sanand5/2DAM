package com.dam.pmdm.activity_08.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dam.pmdm.activity_08.screens.*

@Composable
@Preview
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.MainScreen.route ){
        composable(
            route = AppScreens.MainScreen.route
            ){
            MainScreen(navController)
        }
        composable(
            route= AppScreens.RegistrationScreen.route + "/{paramtext}",
            arguments = listOf(
                navArgument(name="paramtext") {type= NavType.StringType}
            )){
            RegistrationScreen(navController, it.arguments?.getString("paramText"))
        }
        composable(
            route= AppScreens.SummaryScreen.route + "/{bread}/{topings}/{free}",
            arguments = listOf(
                navArgument(name="bread") {type= NavType.StringType},
                navArgument(name="topings") {type= NavType.StringType},
                navArgument(name="free") {type= NavType.StringType}
            )){
            val bread = it.arguments?.getString("bread")
            val topings = it.arguments?.getString("topings")
            val free = it.arguments?.getString("free")

            SummaryScreen(navController, bread, topings, free)
        }
        composable(
            route= AppScreens.ExitScreen.route + "/{paramtext}",
            arguments = listOf(
                navArgument(name="paramtext") {type= NavType.StringType}
            )){
            ExitScreen(navController, it.arguments?.getString("paramText"))
        }
    }
}
