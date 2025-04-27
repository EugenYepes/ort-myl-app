package com.ar.mylapp

/*
* Definimos una clase sellada (sealed class) llamada "Screens" para contener todas las rutas de pantalla.
* Usamos un objeto de datos para cada pantalla, lo que nos permite centralizar las rutas y evitar errores de escritura.
* Cada objeto dentro de "Screens" tiene un valor "screen" que es el nombre único de la ruta de la pantalla.
* Es simplemente una forma de escribir una unica vez los nombres de las posibles pantallas
*
* Por ejemplo cuando en ButtomAppBar utilicemos Screens.Account.screen == "account"
*/

sealed class Screens (val screen: String) {
    data object Account: Screens("account")
    data object Cards: Screens("cards")
    data object Decks: Screens("decks")
    data object Guidebook: Screens("guidebook")
    data object Hand: Screens("hand")
    data object Home: Screens("home")
    data object Stores: Screens("stores")
}