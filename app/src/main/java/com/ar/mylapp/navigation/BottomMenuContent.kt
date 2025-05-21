package com.ar.mylapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.ar.mylapp.R

data class BottomMenuContent(
    val icon: ImageVector,
    val label: String,
    val route: String,
    val section: String
)

@Composable
fun getLogInBottomMenuContent(): List<BottomMenuContent> {
    return listOf(
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.home_icon), "Inicio", Screens.Home.screen, "Home"),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.cards_icon), "Cartas", Screens.Cards.screen, "Cards"),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.decks_icon), "Mazos", Screens.Decks.screen, "Decks"),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.store_icon), "Tiendas", Screens.Stores.screen, "Stores"),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.guide_icon), "Guia", Screens.Guidebook.screen, "Guidebook"),
    )
}

@Composable
fun getNoLogInBottomMenuContent(): List<BottomMenuContent> {
    return listOf(
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.login_icon), "Iniciar Sesion", Screens.Login.screen, "Login"),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.register_icon), "Registrarme", Screens.Register.screen, "Register"),
    )
}