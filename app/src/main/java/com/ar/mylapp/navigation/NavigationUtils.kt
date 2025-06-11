package com.ar.mylapp.navigation

import androidx.compose.runtime.Composable
import com.ar.mylapp.R

fun getHomeButtonsGridInfo() : List<Triple<String, Int, String>> {
    return listOf(
        Triple("CARTAS", R.drawable.cards_icon, Screens.Cards.screen),
        Triple("MAZOS", R.drawable.decks_icon, Screens.Decks.screen),
        Triple("CUENTA", R.drawable.account_icon, Screens.Account.screen),
        Triple("TIENDAS", R.drawable.store_icon, Screens.Stores.screen),
        Triple("GUÍA", R.drawable.guide_icon, Screens.Guidebook.screen),
    )
}

fun getSectionForRoute(route: String?): String? {
    return when {
        route == Screens.Home.screen -> "Home"
        route == Screens.Cards.screen || route?.startsWith(Screens.CardDetail.screen) == true -> "Cards"
        route == Screens.Decks.screen -> "Decks"
        route == Screens.Account.screen -> "Account"
        route == Screens.Stores.screen -> "Stores"
        route == Screens.StoreDetail.screen -> "StoreDetail"
        route == Screens.Hand.screen -> "Hand"
        route == Screens.Guidebook.screen -> "Guidebook"
        route == Screens.Welcome.screen -> "Welcome"
        route == Screens.Login.screen -> "Login"
        route == Screens.Register.screen -> "Register"
        route == Screens.RestorePassword.screen -> "RestorePassword"
        route == Screens.RegisterUser.screen -> "Register"
        route == Screens.RegisterStore.screen -> "Register"
        else -> null
    }
}

fun showTopBar(
    currentRoute: String?
): Boolean {
    val noTopBarRoutes = getNoTopBarRoutes()
    return currentRoute !in noTopBarRoutes
}

private fun getNoTopBarRoutes(): List<String> {
    return listOf(
        Screens.Welcome.screen,
        Screens.Login.screen,
        Screens.Register.screen,
        Screens.RegisterUser.screen,
        Screens.RegisterStore.screen,
        Screens.RestorePassword.screen
    )
}

@Composable
fun AuthGate(
    isAllowed: Boolean,
    onAllowed: @Composable () -> Unit,
    onDenied: @Composable () -> Unit
) {
    if (isAllowed) {
        onAllowed()
    } else {
        onDenied()
    }
}