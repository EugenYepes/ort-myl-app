package com.ar.mylapp.screens.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.auth.FirebaseAuthManager
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button4
import com.ar.mylapp.components.text.Text4
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun AccountScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    topBarViewModel: TopBarViewModel
){

    val context = LocalContext.current

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
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = {
                        // TODO: ir a la pantalla de editar perfil
                        navController.navigate(Screens.EditProfile.screen)
                    }
                ) {
                    Text("Actualizar Perfil")
                }

                Button(
                    onClick = {
                        FirebaseAuthManager.logout(context)
                        userAuthenticationViewModel.clearSession()
                        navController.navigate(Screens.Login.screen) {
                            popUpTo("home") { inclusive = true }
                        }
                    }
                ) {
                    Text("Cerrar sesión")
                }
            }
            Button4(
                text = "Eliminar Cuenta",
                onClick = { userAuthenticationViewModel.clearSession() }
            )
        }
    }
}