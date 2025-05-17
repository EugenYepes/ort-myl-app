package com.ar.mylapp.navigation

fun getSectionForRoute(route: String?): String? {
    return when {
        route == Screens.Home.screen -> "Home"
        route == Screens.Cards.screen || route?.startsWith(Screens.CardDetail.screen) == true -> "Cards"
        route == Screens.Decks.screen -> "Decks"
        route == Screens.Account.screen -> "Account"
        route == Screens.Stores.screen -> "Stores"
        route == Screens.Hand.screen -> "Hand"
        route == Screens.Guidebook.screen -> "Guidebook"
        route == Screens.Welcome.screen -> "Welcome"
        route == Screens.Login.screen -> "Login"
        route == Screens.Register.screen -> "Register"
        route == Screens.RestorePassword.screen -> "RestorePassword"
        route == Screens.RegisterUsuario.screen -> "RegisterUsuario"
        route == Screens.RegisterTienda.screen -> "RegisterTienda"
        else -> null
    }
}