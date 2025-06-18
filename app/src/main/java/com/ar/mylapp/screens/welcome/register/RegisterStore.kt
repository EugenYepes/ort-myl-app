package com.ar.mylapp.screens.welcome.register

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
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

@SuppressLint("ConfigurationScreenWidthHeight")
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

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp

    val logoHeight = if (screenHeightDp < 700) 190.dp else 160.dp

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState())
        ) {
            ImageLogoMyl(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(logoHeight)
            )
            Text3(
                text = stringResource(R.string.required_fields)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                InputOne(
                    label = stringResource(R.string.store_name) + stringResource(R.string.required_field_icon),
                    value = userAuthenticationViewModel.storeName,
                    onValueChange = { userAuthenticationViewModel.storeName = it }
                )

                InputOne(
                    label = stringResource(R.string.store_adress) + stringResource(R.string.required_field_icon),
                    value = userAuthenticationViewModel.address,
                    onValueChange = { userAuthenticationViewModel.address = it }
                )

                InputOne(
                    label = stringResource(R.string.store_phone),
                    value = userAuthenticationViewModel.phone,
                    onValueChange = { userAuthenticationViewModel.phone = it }
                )

                InputOne(
                    label = stringResource(R.string.email) + stringResource(R.string.required_field_icon),
                    value = userAuthenticationViewModel.email,
                    onValueChange = { userAuthenticationViewModel.email = it }
                )

                InputOne(
                    label = stringResource(R.string.password) + stringResource(R.string.required_field_icon),
                    value = userAuthenticationViewModel.password,
                    onValueChange = { userAuthenticationViewModel.password = it },
                    isPassword = true
                )

                InputOne(
                    label = stringResource(R.string.confirm_password) + stringResource(R.string.required_field_icon),
                    value = userAuthenticationViewModel.confirmPassword,
                    onValueChange = { userAuthenticationViewModel.confirmPassword = it },
                    isPassword = true
                )

                userAuthenticationViewModel.error?.let {
                    Text5(
                        text = it,
                        modifier = Modifier.padding(4.dp)
                    )
                }

                Spacer(modifier = Modifier.size(8.dp))
                Button1(
                    onClick = { userAuthenticationViewModel.onRegisterClicked(isStore = true) },
                    text = stringResource(R.string.register_mayus)
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy((-10).dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text3(
                    text = stringResource(R.string.has_an_account)
                )
                Button5(
                    onClick = { navController.navigate(Screens.Login.screen) },
                    text = stringResource(R.string.login_mayus),
                )
            }
        }
    }
}