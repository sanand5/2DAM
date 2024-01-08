package com.dam_pmdm.screensnavegation.navigation

sealed class AppScreen(val route: String) {
    object screen1 : AppScreen("MainScreen")
    object screen2 : AppScreen("RegistrationScreen")
    object screen3 : AppScreen("SummaryScreen")
    object screen4 : AppScreen("ExitScreen")
}