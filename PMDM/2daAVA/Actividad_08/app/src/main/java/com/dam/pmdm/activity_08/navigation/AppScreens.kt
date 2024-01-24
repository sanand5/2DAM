package com.dam.pmdm.activity_08.navigation

sealed class AppScreens(val route: String) {
    object MainScreen : AppScreens("MainScreen")
    object RegistrationScreen : AppScreens("RegistrationScreen")
    object SummaryScreen : AppScreens("SummaryScreen")
    object ExitScreen : AppScreens("ExitScreen")
}
