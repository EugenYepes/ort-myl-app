package com.ar.mylapp.screens.welcome.register

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
fun RegisterTiendaScreen(
    navController: NavController
) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ImageBackground()
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.TopCenter)
        ) {
            ImageLogoMyl(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(150.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 5.dp)
            ) {
                InputOne(
                    label = "Nombre de la Tienda"
                )
                InputOne(
                    label = "Dirección"
                )
                InputOne(
                    label = "Teléfono"
                )
                InputOne(
                    label = "Correo Electrónico"
                )
                InputOne(
                    label = "Contraseña"
                )
                InputOne(
                    label = "Confirmar Contraseña"
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Button1(
                    onClick = {}, text = "REGISTRARSE"
                )
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
