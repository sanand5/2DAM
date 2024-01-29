package com.dam.pmdm.activity_10.ui.app.ui.navigation

sealed class AppScreens(val route: String) {
    object MainScreen : AppScreens("MainScreen")
    object RegistrationScreen : AppScreens("RegistrationScreen")
    object SummaryScreen : AppScreens("SummaryScreen")
    object ExitScreen : AppScreens("ExitScreen")
}
