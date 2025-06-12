package com.meuapp.swithcfmapp.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Home : Screen("home")
    object Loading : Screen("loading")
    object Result : Screen("result")
}


//aqui, são as navegações do programa, criando rotas e localizando elas (de forma fixa/selada) de cada tela