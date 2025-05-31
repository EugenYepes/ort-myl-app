package com.ar.mylapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.ar.mylapp.auth.UserAuthenticationViewModel

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
        route == Screens.RegisterUser.screen -> "RegisterUser"
        route == Screens.RegisterStore.screen -> "RegisterStore"
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

@Composable
fun NavigateOnRegistrationSuccess(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    popUpToScreen: String,
    destinationScreen: String
) {
    LaunchedEffect(userAuthenticationViewModel.registrationSuccess) {
        if (userAuthenticationViewModel.registrationSuccess) {
            navController.navigate(destinationScreen) {
                popUpTo(popUpToScreen) {
                    inclusive = true
                }
            }
            userAuthenticationViewModel.resetRegistrationState()
        }
    }
}

@Composable
fun NavigateOnLogInSuccess(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    popUpToScreen: String,
    destinationScreen: String
) {
    LaunchedEffect(userAuthenticationViewModel.token) {
        if (!userAuthenticationViewModel.token.isNullOrEmpty()) {
            navController.navigate(destinationScreen) {
                popUpTo(popUpToScreen) {
                    inclusive = true
                }
            }
        }
    }
}
