package com.ar.mylapp.screens.welcome.login

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
import com.ar.mylapp.components.entryData.InputOne
import com.ar.mylapp.components.image.ImageBackground
import com.ar.mylapp.components.image.ImageLogoMyl
import com.ar.mylapp.components.text.Text3
import com.ar.mylapp.navigation.Screens


@Composable
fun LoginScreen(
    navController: NavController
    //Debería recibir LoginViewModel
    //viewModel: LoginViewModel = viewModel()
)
// : ViewModel()
{
    /*
    var email = viewModel.email
    var password = viewModel.password
    var error = viewModel.error
    var token = viewModel.token
    */

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ImageBackground()
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
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 10.dp)
            ) {
                InputOne(
                    label = "Correo Electrónico"
                )
                InputOne(
                    label = "Contraseña"
                )
                Button5(
                    onClick = { navController.navigate(Screens.RestorePassword.screen)},
                    text = "Recuperar Contraseña"
                )
                Button1(
                    onClick = {},
                    text = "INICIAR SESIÓN"
                )
                Text3(
                    text = "¿No tienes una cuenta?"
                )
                Button5(
                    onClick = {},
                    text = "REGISTRARSE"
                )
            }

        }
    }
}
