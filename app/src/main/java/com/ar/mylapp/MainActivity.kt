package com.ar.mylapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ar.mylapp.components.bottonBar.MyBottomAppBar
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.navigation.getSectionForRoute
import com.ar.mylapp.screens.account.AccountScreen
import com.ar.mylapp.screens.card.CardDetail
import com.ar.mylapp.screens.card.CardsScreen
import com.ar.mylapp.screens.deck.DecksScreen
import com.ar.mylapp.screens.guidebook.GuidebookScreen
import com.ar.mylapp.screens.hand.HandScreen
import com.ar.mylapp.screens.home.HomeScreen
import com.ar.mylapp.screens.store.StoresScreen
import com.ar.mylapp.ui.theme.MYLAPPTheme
import com.ar.mylapp.viewmodel.CardViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MYLAPPTheme {
                // Creas el NavController en el Activity
                val navController = rememberNavController()

                // Crear una instancia del ViewModel
                val cardViewModel: CardViewModel = viewModel()

                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.background),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                    // Usamos Scaffold para gestionar la BottomAppBar y el contenido
                    Scaffold(
                        containerColor = Color.Transparent,
                        bottomBar = {
                            // Mostramos la BottomAppBar solo si la pantalla actual no es Stores o Account
                            if (shouldShowBottomAppBar(navController)) {
                                MyBottomAppBar(navController)
                            }
                        }
                    ) { paddingValues ->
                        // El NavHost gestiona la navegación entre pantallas
                        NavHost(
                            navController = navController,
                            startDestination = Screens.Home.screen,
                            modifier = Modifier.padding(paddingValues)
                        ) {
                            composable(Screens.Home.screen) { HomeScreen(navController) }
                            composable(Screens.Cards.screen) { CardsScreen(navController, cardViewModel) }
                            composable(
                                route = "${Screens.CardDetail.screen}/{cardId}",
                                arguments = listOf(navArgument("cardId") { type = NavType.IntType })
                            ) { backStackEntry ->
                                val cardId = backStackEntry.arguments?.getInt("cardId") ?: return@composable
                                val card = cardViewModel.cards.find { it.cardId == cardId }
                                if (card != null) {
                                    CardDetail(card)
                                }
                            }
                            composable(Screens.Decks.screen) { DecksScreen(navController) }
                            composable(Screens.Account.screen) { AccountScreen(navController) }
                            composable(Screens.Hand.screen) { HandScreen(navController) }
                            composable(Screens.Stores.screen) { StoresScreen(navController) }
                            composable(Screens.Guidebook.screen) { GuidebookScreen(navController) }
                        }
                    }
                }
            }
        }
    }

    // Función que decide si mostrar la BottomAppBar o no
    @Composable
    private fun shouldShowBottomAppBar(navController: NavController): Boolean {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        val currentSection = getSectionForRoute(currentRoute)
        // Listar las secciones donde NO queremos mostrar la BottomAppBar
        return currentSection !in listOf("Account" /* o cualquier otra sección */)
    }
}