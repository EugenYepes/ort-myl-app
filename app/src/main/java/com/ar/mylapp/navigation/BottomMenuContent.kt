package com.ar.mylapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
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
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.home_icon), stringResource(R.string.bottom_menu_home_label), Screens.Home.screen, stringResource(R.string.bottom_menu_home_section)),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.cards_icon), stringResource(R.string.bottom_menu_cards_label), Screens.Cards.screen, stringResource(R.string.bottom_menu_home_section)),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.decks_icon), stringResource(R.string.bottom_menu_decks_label), Screens.Decks.screen, stringResource(R.string.bottom_menu_home_section)),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.store_icon), stringResource(R.string.bottom_menu_store_label), Screens.Stores.screen, stringResource(R.string.bottom_menu_home_section)),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.guide_icon), stringResource(R.string.bottom_menu_guide_label), Screens.Guidebook.screen, stringResource(R.string.bottom_menu_home_section)),
    )
}

@Composable
fun getNoLogInBottomMenuContent(): List<BottomMenuContent> {
    return listOf(
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.login_icon), stringResource(R.string.login_label), Screens.Login.screen, stringResource(R.string.login_section)),
        BottomMenuContent(ImageVector.vectorResource(id = R.drawable.register_icon), stringResource(R.string.register_label), Screens.Register.screen, stringResource(R.string.register_section)),
    )
}