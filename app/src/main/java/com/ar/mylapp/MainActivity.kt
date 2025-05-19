package com.ar.mylapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ar.mylapp.components.bottonBar.MyBottomAppBar
import com.ar.mylapp.components.image.ImageBackground
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.screens.account.AccountScreen
import com.ar.mylapp.screens.card.CardDetail
import com.ar.mylapp.screens.card.CardsScreen
import com.ar.mylapp.screens.deck.DecksScreen
import com.ar.mylapp.screens.guidebook.GuidebookScreen
import com.ar.mylapp.screens.hand.HandScreen
import com.ar.mylapp.screens.home.HomeScreen
import com.ar.mylapp.screens.store.StoresScreen
import com.ar.mylapp.screens.welcome.WelcomeScreen
import com.ar.mylapp.screens.welcome.login.LoginScreen
import com.ar.mylapp.screens.welcome.register.RegisterScreen
import com.ar.mylapp.screens.welcome.register.RegisterStoreScreen
import com.ar.mylapp.screens.welcome.register.RegisterUserScreen
import com.ar.mylapp.screens.welcome.restorePassword.RestorePasswordScreen
import com.ar.mylapp.ui.theme.MYLAPPTheme
import com.ar.mylapp.viewmodel.BottomBarViewModel
import com.ar.mylapp.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MYLAPPTheme {
                val navController = rememberNavController()

                val cardViewModel: CardViewModel = viewModel()
                val bottomBarViewModel: BottomBarViewModel = viewModel()

                LaunchedEffect(navController) {
                    navController.currentBackStackEntryFlow.collect { entry ->
                        val route = entry.destination.route
                        bottomBarViewModel.updateSectionFromRoute(route)
                    }
                }

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {

                    ImageBackground()
                    
                    Scaffold(
                        containerColor = Color.Transparent,
                        bottomBar = {
                            MyBottomAppBar(navController, bottomBarViewModel)
                        }
                    ) { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = Screens.Home.screen,
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable(Screens.Home.screen) { HomeScreen(navController) }
                            composable(Screens.Cards.screen) {
                                CardsScreen(
                                    navController,
                                    cardViewModel
                                )
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
                            composable(Screens.Decks.screen) { DecksScreen(navController) }
                            composable(Screens.Account.screen) { AccountScreen(navController) }
                            composable(Screens.Hand.screen) { HandScreen(navController) }
                            composable(Screens.Stores.screen) { StoresScreen(navController) }
                            composable(Screens.Guidebook.screen) { GuidebookScreen(navController) }
                            composable(Screens.Welcome.screen) { WelcomeScreen(navController) }
                            composable(Screens.Login.screen) { LoginScreen(navController) }
                            composable(Screens.Register.screen) { RegisterScreen(navController) }
                            composable(Screens.RestorePassword.screen) { RestorePasswordScreen(navController) }
                            composable(Screens.RegisterUsuario.screen) { RegisterUserScreen(navController) }
                            composable(Screens.RegisterTienda.screen) { RegisterStoreScreen(navController) }
                        }
                    }
                }
            }
        }
    }
}