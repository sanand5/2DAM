package com.dam.pmdm.peliculasapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam.pmdm.peliculasapp.ui.screens.AddMovieScreen
import com.dam.pmdm.peliculasapp.ui.screens.LoginScreen
import com.dam.pmdm.peliculasapp.ui.screens.MovieScreen
import com.google.firebase.auth.FirebaseUser


@Preview(showBackground = true)
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authSate = remember{ mutableStateOf<FirebaseUser?>(null) }
    NavHost(
        navController = navController,
        startDestination = if (authSate.value == null) AppScreen.LoginScreen.route else AppScreen.MovieScreen.route
    ){
        composable(route = AppScreen.LoginScreen.route){
            LoginScreen(
                onLoginSuccess = {
                    authSate.value = it
                    navController.navigate(AppScreen.MovieScreen.route)
                }
            )
        }

        composable (route= AppScreen.MovieScreen.route) {
            MovieScreen(
                onAddMovie = {
                    navController.navigate(AppScreen.AddMovieScreen.route)
                },
                onLogout = {
                    navController.navigate(AppScreen.LoginScreen.route)
                }
            )
        }
        composable(route= AppScreen.AddMovieScreen.route) {
            AddMovieScreen(
                closeAddCompleteMovie={
                    navController.navigate(AppScreen.MovieScreen.route)
                }
            )
        }
        }
    }