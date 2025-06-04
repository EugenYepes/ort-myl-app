package com.ar.mylapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.FirebaseAuthManager
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    topBarViewModel: TopBarViewModel
){
    val title = stringResource(R.string.topbar_home_title)
    val subtitle = stringResource(R.string.topbar_home_subtitle)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title, subtitle)
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Home Screen",
                fontSize = 30.sp,
                color = GoldDark
            )
            Button1(
                onClick = { navController.navigate(Screens.Cards.screen) },
                text = "Go to Cards Screen"
            )
            Button1(
                onClick = { navController.navigate(Screens.Account.screen) },
                text = "Go to Account Screen"
            )
            //Para testear patalla de Welcome (Login y Register)
            Button1(
                onClick = { navController.navigate(Screens.Welcome.screen) },
                text = "Go to Welcome Screen"
            )
            Button1(
                onClick = { navController.navigate(Screens.Stores.screen) },
                text = "Go to Store Screen"
            )
            Spacer(modifier = Modifier.height(24.dp))
            // Se agregar boton de logout para pruebas
            Button(
                onClick = {
                    FirebaseAuthManager.logout(context)
                    // Limpiar la sesión del ViewModel
                    // para que no rebote entre Home y Login
                    userAuthenticationViewModel.clearSession()
                    navController.navigate("login") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            ) {
                Text("Cerrar sesión")
            }
        }
    }
}