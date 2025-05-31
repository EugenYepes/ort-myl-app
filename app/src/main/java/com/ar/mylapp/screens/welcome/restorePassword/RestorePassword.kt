package com.ar.mylapp.screens.welcome.restorePassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button2
import com.ar.mylapp.components.entryData.InputOne
import com.ar.mylapp.components.image.ImageLogoMyl
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.components.title.Title2
import com.ar.mylapp.navigation.Screens

@Composable
fun RestorePasswordScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel = viewModel()

)
{
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
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
                verticalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 30.dp)
            ) {
                Title2(
                    title = "Recuperar Contraseña"
                )
                Text5(
                    text = "Por favor, ingrese su correo electrónico para recuperar su contraseña"
                )

                InputOne(
                    label = "Correo Electrónico",
                    value = userAuthenticationViewModel.email,
                    onValueChange = { userAuthenticationViewModel.email = it }
                )
                Button2(
                    onClick = {
                        userAuthenticationViewModel.onResetPasswordClicked {
                            // Volver a Login
                            navController.navigate(Screens.Login.screen) {
                                popUpTo(Screens.RestorePassword.screen) { inclusive = true }
                            }
                        }
                    },
                    text = "Recuperar Contraseña"
                )
                userAuthenticationViewModel.error?.let {
                    Text5(text = it)
                }
            }

        }
    }
}
