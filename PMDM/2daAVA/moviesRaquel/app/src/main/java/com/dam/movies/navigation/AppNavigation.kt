    package com.dam.movies.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dam.movies.ui.screens.AddMovieScreen
import com.dam.movies.ui.screens.LoginScreen
import com.dam.movies.ui.screens.MovieScreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val authState = remember { mutableStateOf<FirebaseUser?>(null) }

    NavHost (
        navController = navController,
        startDestination = if(authState.value == null) AppScreens.LoginScreen.route else AppScreens.MovieScreen.route
    )
    {
        composable (route = AppScreens.LoginScreen.route) {
            LoginScreen(
                onLoginSuccess = {
                    authState.value = it
                    navController.navigate(AppScreens.MovieScreen.route)
                }
            )
        }

        composable (route= AppScreens.MovieScreen.route) {
            MovieScreen(
                onAddMovie={
                    navController.navigate(AppScreens.AddMovieScreen.route)
                },
                onLogout = {
                    authState.value = null
                    navController.navigate(AppScreens.LoginScreen.route)
               }
            )
        }

        composable(route= AppScreens.AddMovieScreen.route) {
            AddMovieScreen(
                closeAddCompleteMovie={
                    navController.navigate(AppScreens.MovieScreen.route)
                }
            )
        }
    }
}