package com.dam.pmdm.peliculasapp.navigation

sealed class AppScreen(val route: String) {
    object LoginScreen : AppScreen("LoginScreen")
    object MovieScreen : AppScreen("MovieScreen")
    object AddMovieScreen : AppScreen("AddMovieScreen")
}