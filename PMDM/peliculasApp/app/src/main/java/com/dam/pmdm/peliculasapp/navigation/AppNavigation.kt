package com.dam.pmdm.peliculasapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam.pmdm.peliculasapp.navigation.AppScreen
import com.dam.pmdm.peliculasapp.ui.screens.LoginScreen

@Preview(showBackground = true)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreen.LoginScreen.route
    ){
        composable(route = AppScreen.LoginScreen.route){
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(AppScreen.MovieScreen.route)
                }
            )
        }

//        composable(route = AppScreen.LoginScreen.route){
//            MovieScreen(
//                onAddMovie = {
//                    navController.navigate(AppScreen.AddMovieScreen.route)
//                }
//            )
//        }
//
//
//        composable(route = AppScreen.LoginScreen.route){
//            AddMovieScreen(
//                onAddMovie = {
//                    navController.navigate(AppScreen.MovieScreen.route)
//                }
//            )
//        }
    }

}