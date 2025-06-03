package com.ar.mylapp.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.screens.account.AccountScreen
import com.ar.mylapp.screens.card.AdvancedSearchScreen
import com.ar.mylapp.screens.card.CardDetail
import com.ar.mylapp.screens.card.CardsScreen
import com.ar.mylapp.screens.card.FilteredResultsScreen
import com.ar.mylapp.screens.deck.DecksScreen
import com.ar.mylapp.screens.guidebook.GuidebookScreen
import com.ar.mylapp.screens.home.HomeScreen
import com.ar.mylapp.screens.store.StoreDetailScreen
import com.ar.mylapp.screens.store.StoresScreen
import com.ar.mylapp.screens.welcome.WelcomeScreen
import com.ar.mylapp.screens.welcome.login.LoginScreen
import com.ar.mylapp.screens.welcome.register.RegisterScreen
import com.ar.mylapp.screens.welcome.register.RegisterStoreScreen
import com.ar.mylapp.screens.welcome.register.RegisterUserScreen
import com.ar.mylapp.screens.welcome.restorePassword.RestorePasswordScreen
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun NavigationScreens(
    navController: NavHostController,
    paddingValues: PaddingValues,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    cardViewModel: CardViewModel,
    topBarViewModel: TopBarViewModel,
    isLoggedIn: Boolean
){
    NavHost(
        navController = navController,
        startDestination = Screens.Home.screen,
        modifier = Modifier.padding(paddingValues),
    ){

        //? Pantallas accesibles para todos
        //* Home
        composable(Screens.Home.screen) {
            HomeScreen(navController, userAuthenticationViewModel, topBarViewModel)
        }

        //* Cards
        composable(Screens.Cards.screen) {
            CardsScreen(navController, cardViewModel, topBarViewModel)
        }

        composable(
            route = "${Screens.CardDetail.screen}/{cardId}",
            arguments = listOf(navArgument("cardId") { type = NavType.IntType })
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getInt("cardId") ?: return@composable
            CardDetail(id = cardId, topBarViewModel = topBarViewModel, viewModel = cardViewModel)
        }

        composable(Screens.AdvanceSearch.screen) {
            AdvancedSearchScreen(
                onFiltersApplied = { filters ->
                    cardViewModel.loadFilteredCards(filters)
                    navController.navigate(Screens.FilteredResults.screen)
                },
                topBarViewModel = topBarViewModel
            )
        }

        composable(Screens.FilteredResults.screen) {
            FilteredResultsScreen(
                navController = navController,
                viewModel = cardViewModel,
                topBarViewModel = topBarViewModel
            )
        }

        //* Stores
        composable(Screens.Stores.screen) {
            StoresScreen(navController, topBarViewModel)
        }
        composable(Screens.StoreDetail.screen) {
            StoreDetailScreen(navController, topBarViewModel)
        }

        //* Guidebook
        composable(Screens.Guidebook.screen) {
            GuidebookScreen(navController, topBarViewModel)
        }

        //? Pantallas solo para usuarios sin sesion iniciada
        //* Welcome
        composable(Screens.Welcome.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel, topBarViewModel) },
                onDenied = { WelcomeScreen(navController) }
            )
        }

        //* Log In
        composable(Screens.Login.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel, topBarViewModel) },
                onDenied = {  LoginScreen(navController, userAuthenticationViewModel) }
            )
        }

        //* Register
        composable(Screens.Register.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel, topBarViewModel) },
                onDenied = { RegisterScreen(navController) }
            )
        }
        composable(Screens.RegisterUser.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel, topBarViewModel) },
                onDenied = { RegisterUserScreen(navController, userAuthenticationViewModel) }
            )
        }
        composable(Screens.RegisterStore.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel, topBarViewModel) },
                onDenied = { RegisterStoreScreen(navController, userAuthenticationViewModel) }
            )
        }

        //* Restore Password
        composable(Screens.RestorePassword.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { HomeScreen(navController, userAuthenticationViewModel, topBarViewModel) },
                onDenied = { RestorePasswordScreen(navController) }
            )
        }

        //? Pantallas solo para usuarios con sesion iniciada
        //* Decks
        composable(Screens.Decks.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { DecksScreen(navController, topBarViewModel) },
                onDenied = { WelcomeScreen(navController) }
            )
        }

        //* Account
        composable(Screens.Account.screen) {
            AuthGate(
                isAllowed = isLoggedIn,
                onAllowed = { AccountScreen(navController, userAuthenticationViewModel, topBarViewModel) },
                onDenied = { WelcomeScreen(navController) }
            )
        }
    }
}