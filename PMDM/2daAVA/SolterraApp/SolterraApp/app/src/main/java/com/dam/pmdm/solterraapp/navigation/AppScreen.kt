package com.dam.pmdm.solterraapp.navigation

sealed class AppScreen(val route: String) {
    object WelcomeScr : AppScreen("WelcomeScr")
    object AddProductScr : AppScreen("AddProductScr")
    object ExitScr : AppScreen("ExitScr")
    object LogRegScr : AppScreen("LogRegScr")
    object ProfileScr : AppScreen("ProfileScr")
    object ShoppingCartScr : AppScreen("ShoppingCartScr")
}
