package com.dam.pmdm.navegation.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dam.pmdm.navegation.screens.FirstScreen
import com.dam.pmdm.navegation.screens.SecondScreen
@Composable
fun AppNavigation(){

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.FirstScreen.route ){
        composable(route = AppScreens.FirstScreen.route +"/{paramText}",
            arguments = listOf(navArgument(name = "paramText"){type = NavType.StringType})
            ){
            FirstScreen(navController)
        }
        composable(route= AppScreens.ScondScreen.route){
            SecondScreen(navController, it.arguments?.getString("paramText"))
        }
    }
}
