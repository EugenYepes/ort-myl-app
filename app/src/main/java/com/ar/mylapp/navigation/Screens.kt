package com.ar.mylapp.navigation

/*
* Definimos una clase sellada (sealed class) llamada "Screens" para contener todas las rutas de pantalla.
* Usamos un objeto de datos para cada pantalla, lo que nos permite centralizar las rutas y evitar errores de escritura.
* Cada objeto dentro de "Screens" tiene un valor "screen" que es el nombre único de la ruta de la pantalla.
* Es simplemente una forma de escribir una unica vez los nombres de las posibles pantallas
*
* Por ejemplo cuando en ButtomAppBar utilicemos Screens.Account.screen == "account"
*/
sealed class Screens (val screen: String) {
    // Account
    data object Account: Screens("account")

    // Cards
    data object Cards: Screens("cards")
    data object CardDetail: Screens("cardDetail") {
        fun withArgs(cardId: Int): String = "cardDetail/$cardId"
    }
    data object AdvanceSearch: Screens("advanceSearch")
    data object FilteredResults: Screens("filteredResults")

    // Decks
    data object Decks: Screens("decks")
    data object DeckDetail : Screens("deckDetail") {
        fun withArgs(deckId: Int) = "deckDetail/$deckId"
    }

    // Guidebook
    data object Guidebook: Screens("guidebook")

    // Hand
    data object Hand: Screens("hand")

    // Home
    data object Home: Screens("home")

    // Stores
    data object Stores: Screens("stores")
    data object StoreDetail: Screens("storeDetail")

    // Welcome
    data object Welcome: Screens("welcome")

    // Login
    data object Login: Screens("login")

    // Register
    data object Register: Screens("register")
    data object RegisterUser: Screens("registerUser")
    data object RegisterStore: Screens("registerStore")

    // RestorePassword
    data object RestorePassword: Screens("restorePassword")
}