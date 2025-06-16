package com.ar.mylapp.screens.welcome.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.buttons.Button5
import com.ar.mylapp.components.entryData.InputOne
import com.ar.mylapp.components.image.ImageLogoMyl
import com.ar.mylapp.components.text.Text3
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.viewmodel.DecksViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    decksViewModel: DecksViewModel
)
{
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
                InputOne(
                    label = stringResource(R.string.email),
                    value = userAuthenticationViewModel.email,
                    onValueChange = { userAuthenticationViewModel.email = it }
                )
                InputOne(
                    label = stringResource(R.string.password),
                    value = userAuthenticationViewModel.password,
                    onValueChange = { userAuthenticationViewModel.password = it },
                    isPassword = true
                )

                userAuthenticationViewModel.error?.let {
                    Text5(
                        text = it,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Button5(
                    onClick = { navController.navigate(Screens.RestorePassword.screen)},
                    text = stringResource(R.string.restore_password)
                )
                Button1(
                    onClick = { userAuthenticationViewModel.onLoginClicked(navController, decksViewModel) },
                    text = stringResource(R.string.login_mayus)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy((-10).dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text3(text = stringResource(R.string.no_account))
                    Button5(
                        onClick = {navController.navigate(Screens.Register.screen)},
                        text = stringResource(R.string.register_mayus)
                    )
                }
            }
        }
    }
}
