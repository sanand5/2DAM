package com.dam.pmdm.solterraapp.navigation

sealed class AppScreen(val route: String) {
    object WelcomeScr : AppScreen("WelcomeScr")
    object LogRegScr : AppScreen("LogRegScr")
    object ShoppingCartScr : AppScreen("ShoppingCartScr")
    object AddProductScr : AppScreen("AddProductScr")
    object ProfileScr : AppScreen("ProfileScr")
    object ExitScr : AppScreen("ExitScr")
}
