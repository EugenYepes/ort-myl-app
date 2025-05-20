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
import androidx.navigation.compose.rememberNavController
import com.ar.mylapp.components.bottonBar.MyBottomAppBar
import com.ar.mylapp.ui.theme.MYLAPPTheme
import com.ar.mylapp.viewmodel.BottomBarViewModel
import com.ar.mylapp.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.image.ImageBackground
import com.ar.mylapp.navigation.NavigationScreens

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MYLAPPTheme {

                val navController = rememberNavController()

                val userAuthenticationViewModel: UserAuthenticationViewModel = viewModel()
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
//                            if(userAuthenticationViewModel.isLoggedIn()){
//                            }
                        }
                    ) { paddingValues ->
                        NavigationScreens(
                            navController = navController,
                            paddingValues = paddingValues,
                            userAuthenticationViewModel = userAuthenticationViewModel,
                            cardViewModel = cardViewModel
                        )
                    }
                }
            }
        }
    }
}