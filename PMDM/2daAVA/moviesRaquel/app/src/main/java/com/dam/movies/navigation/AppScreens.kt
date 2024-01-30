package com.dam.movies.navigation

sealed class AppScreens(val route: String){
    object LoginScreen: AppScreens("login_screen")
    object MovieScreen: AppScreens("movies_screen")
    object AddMovieScreen: AppScreens("add_movie_screen")
}
