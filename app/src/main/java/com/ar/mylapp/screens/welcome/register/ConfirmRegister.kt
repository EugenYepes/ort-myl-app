package com.ar.mylapp.screens.welcome.register

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
import com.ar.mylapp.components.buttons.Button2
import com.ar.mylapp.components.image.ImageLogoMyl
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.components.title.Title2
import com.ar.mylapp.navigation.Screens

@Composable
fun ConfirmRegister(
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
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
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Title2(
                    title = "Su Correo Electrónico ha sido verificado"
                )
                Text5(
                    text = "Gracias por unirte a Mitos y Leyendas Companion!\nA continuacion se le ha enviado un correo electronico a su mail.\nPor favor confirme su registro para poder ingresar"
                )
                Button2(
                    text = "Iniciar Sesion",
                    onClick = { navController.navigate(Screens.Login.screen)}
                )
            }
        }
    }
}