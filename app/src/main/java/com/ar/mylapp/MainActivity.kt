package com.ar.mylapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ar.mylapp.components.bottonBar.MyBottomAppBar
import com.ar.mylapp.ui.theme.MYLAPPTheme
import com.ar.mylapp.viewmodel.BottomBarViewModel
import com.ar.mylapp.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.image.ImageBackground
import com.ar.mylapp.components.topBar.MyTopBar
import com.ar.mylapp.navigation.NavigationScreens
import com.ar.mylapp.viewmodel.TopBarViewModel
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.ar.mylapp.navigation.showTopBar
import com.ar.mylapp.viewmodel.AccountViewModel
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.StoreViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        splashScreen.setOnExitAnimationListener { splashProvider ->
            val splashView = splashProvider.view

            // Fade-out con duración
            splashView.animate()
                .alpha(0f)
                .setDuration(500)
                .withEndAction {
                    splashProvider.remove()
                }
                .start()
        }
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MYLAPPTheme {
                val topBarViewModel: TopBarViewModel = viewModel()
                val navController = rememberNavController()
                val userAuthenticationViewModel: UserAuthenticationViewModel = viewModel()
                val cardViewModel: CardViewModel = viewModel()
                val deckViewModel: DecksViewModel = viewModel()
                val storeViewModel: StoreViewModel = viewModel()
                val bottomBarViewModel: BottomBarViewModel = viewModel()
                val accountViewModel: AccountViewModel = viewModel()
                LaunchedEffect(Unit) {
                    userAuthenticationViewModel.loadToken()
                }

                val token = userAuthenticationViewModel.token
                val isLoggedIn = token != null
                if(isLoggedIn){
                    accountViewModel.getFullUserInfo()
                    deckViewModel.loadDecks(token)
                }


                // Ruta actual
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

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
                        topBar = {
                            if (showTopBar(currentRoute)) {
                                MyTopBar(
                                    topBarViewModel = topBarViewModel,
                                    navController = navController
                                )
                            }
                        },
                        bottomBar = {
                            MyBottomAppBar(
                                navController = navController,
                                bottomBarViewModel = bottomBarViewModel,
                                userAuthenticationViewModel = userAuthenticationViewModel
                            )
                        }
                    ) { paddingValues ->
                        NavigationScreens(
                            navController = navController,
                            paddingValues = paddingValues,
                            userAuthenticationViewModel = userAuthenticationViewModel,
                            cardViewModel = cardViewModel,
                            deckViewModel = deckViewModel,
                            storeViewModel = storeViewModel,
                            topBarViewModel = topBarViewModel,
                            accountViewModel = accountViewModel,
                            isLoggedIn = isLoggedIn,
                        )
                    }
                }
            }
        }
    }
}