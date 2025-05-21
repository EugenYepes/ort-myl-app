package com.ar.mylapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.screens.account.AccountScreen
import com.ar.mylapp.screens.card.CardDetail
import com.ar.mylapp.screens.card.CardsScreen
import com.ar.mylapp.screens.deck.DecksScreen
import com.ar.mylapp.screens.guidebook.GuidebookScreen
import com.ar.mylapp.screens.home.HomeScreen
import com.ar.mylapp.screens.store.StoresScreen
import com.ar.mylapp.screens.welcome.WelcomeScreen
import com.ar.mylapp.screens.welcome.login.LoginScreen
import com.ar.mylapp.screens.welcome.register.RegisterScreen
import com.ar.mylapp.screens.welcome.register.RegisterStoreScreen
import com.ar.mylapp.screens.welcome.register.RegisterUserScreen
import com.ar.mylapp.screens.welcome.restorePassword.RestorePasswordScreen
import com.ar.mylapp.viewmodel.CardViewModel

@Composable
fun NavigationScreens(
    navController: NavHostController,
    paddingValues: PaddingValues,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    cardViewModel: CardViewModel
){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.screen,
        modifier = Modifier.padding(paddingValues)
    ){
        val isLoggedIn by derivedStateOf { userAuthenticationViewModel.token != null }

        //? Pantallas accesibles para todos
        //* Home
        composable(Screens.Home.screen) {
            HomeScreen(navController, userAuthenticationViewModel)
        }

        //* Cards
        composable(Screens.Cards.screen) {
            CardsScreen(navController, cardViewModel)
        }

        composable(
            route = "${Screens.CardDetail.screen}/{cardId}",
            arguments = listOf(navArgument("cardId") { type = NavType.IntType })
        ) { backStackEntry ->
            val cardId =
                backStackEntry.arguments?.getInt("cardId") ?: return@composable
            val card = cardViewModel.cards.find { it.id == cardId }
            if (card != null) {
                CardDetail(card)
            }
        }

        //* Stores
        composable(Screens.Stores.screen) {
            StoresScreen(navController)
        }

        composable(Screens.Guidebook.screen) {
            GuidebookScreen(navController)
        }

        //? Pantallas solo para usuarios sin sesion iniciada
        //* Welcome
        composable(Screens.Welcome.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel) },
                onDenied = { WelcomeScreen(navController) }
            )
        }

        //* Log In
        composable(Screens.Login.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel) },
                onDenied = {  LoginScreen(navController, userAuthenticationViewModel) }
            )
        }

        //* Register
        composable(Screens.Register.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel) },
                onDenied = { RegisterScreen(navController) }
            )
        }
        composable(Screens.RegisterUser.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel) },
                onDenied = { RegisterUserScreen(navController, userAuthenticationViewModel) }
            )
        }
        composable(Screens.RegisterStore.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel) },
                onDenied = { RegisterStoreScreen(navController, userAuthenticationViewModel) }
            )
        }

        //* Restore Password
        composable(Screens.RestorePassword.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel) },
                onDenied = { RestorePasswordScreen(navController) }
            )
        }

        //? Pantallas solo para usuarios con sesion iniciada
        composable(Screens.Decks.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { DecksScreen(navController) },
                onDenied = { WelcomeScreen(navController) }
            )
        }
        composable(Screens.Account.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { AccountScreen(navController, userAuthenticationViewModel) },
                onDenied = { WelcomeScreen(navController) }
            )
        }
    }
}