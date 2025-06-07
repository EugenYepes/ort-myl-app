package com.ar.mylapp.screens.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button4
import com.ar.mylapp.components.text.Text4
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun AccountScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    topBarViewModel: TopBarViewModel
){
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("MI CUENTA")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text4(
                text = "Mail: " + if (userAuthenticationViewModel.email != "") userAuthenticationViewModel.email else "no hay nada",
            )
            Text4(
                text = "Contra: " + if (userAuthenticationViewModel.password != "") userAuthenticationViewModel.password else "no hay nada",
            )
            Button4(
                text = "Cerrar Sesión",
                onClick = { userAuthenticationViewModel.clearSession() }
            )
        }
    }
}