package com.ar.mylapp.screens.welcome.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.buttons.Button5
import com.ar.mylapp.components.entryData.InputOne
import com.ar.mylapp.components.image.ImageLogoMyl
import com.ar.mylapp.components.text.Text3
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.navigation.Screens

@Composable
fun RegisterStoreScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    onRegistered: () -> Unit
) {

    if (userAuthenticationViewModel.navigateToConfirmScreen) {
        userAuthenticationViewModel.navigateToConfirmScreen = false
        onRegistered()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            ImageLogoMyl(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(150.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                InputOne(
                    label = "Nombre de la Tienda",
                    value = userAuthenticationViewModel.storeName,
                    onValueChange = { userAuthenticationViewModel.storeName = it }
                )

                InputOne(
                    label = "Dirección",
                    value = userAuthenticationViewModel.address,
                    onValueChange = { userAuthenticationViewModel.address = it }
                )

                InputOne(
                    label = "Teléfono",
                    value = userAuthenticationViewModel.phone,
                    onValueChange = { userAuthenticationViewModel.phone = it }
                )

                InputOne(
                    label = "Correo Electrónico",
                    value = userAuthenticationViewModel.email,
                    onValueChange = { userAuthenticationViewModel.email = it }
                )

                InputOne(
                    label = "Contraseña",
                    value = userAuthenticationViewModel.password,
                    onValueChange = { userAuthenticationViewModel.password = it },
                    isPassword = true
                )

                InputOne(
                    label = "Confirmar Contraseña",
                    value = userAuthenticationViewModel.confirmPassword,
                    onValueChange = { userAuthenticationViewModel.confirmPassword = it },
                    isPassword = true
                )

                userAuthenticationViewModel.error?.let {
                    Text5(
                        text = it,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))
                Button1(
                    onClick = { userAuthenticationViewModel.onRegisterClicked(isStore = true) },
                    text = "REGISTRARSE"
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy((-10).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text3(
                    text = "¿Ya tienes una cuenta?"
                )
                Button5(
                    onClick = { navController.navigate(Screens.Login.screen) },
                    text = "INICIAR SESIÓN"
                )
            }
        }
    }
}