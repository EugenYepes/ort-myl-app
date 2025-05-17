package com.ar.mylapp.screens.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.buttons.Button5
import com.ar.mylapp.components.image.ImageLogoMyl
import com.ar.mylapp.navigation.Screens

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.TopCenter)
        ) {
            ImageLogoMyl(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 40.dp)
            ) {
                Button1(
                    onClick = { navController.navigate(Screens.Login.screen) },
                    text = "INICIAR SESIÓN"
                )
                Button1(
                    onClick = { navController.navigate(Screens.Register.screen) },
                    text = "REGISTRARSE"
                )
                Button5(
                    onClick = {},
                    text = "APRENDE A JUGAR"
                )
            }

        }
    }
}
