package com.ar.mylapp.navigation

fun getSectionForRoute(route: String?): String? {
    return when (route) {
        Screens.Home.screen -> "Home"
        Screens.Cards.screen, Screens.CardDetail.screen -> "Cards"
        Screens.Decks.screen -> "Decks"
        Screens.Account.screen -> "Account"
        Screens.Stores.screen -> "Stores"
        Screens.Hand.screen -> "Hand"
        Screens.Guidebook.screen -> "Guidebook"
        else -> null
    }
}