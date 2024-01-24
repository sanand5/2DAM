package com.dam.pmdm.navegation.navegation

sealed class AppScreens(val route: String){
    object FirstScreen: AppScreens("firtscreen")
    object ScondScreen: AppScreens("secondscreen")
}
