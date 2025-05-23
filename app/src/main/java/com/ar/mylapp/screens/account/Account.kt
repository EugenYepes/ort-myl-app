package com.ar.mylapp.screens.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.ui.theme.GoldDark
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
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Account Screen",
                fontSize = 30.sp,
                color = GoldDark,
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Text(
                text = "Mail: " + if (userAuthenticationViewModel.email != "") userAuthenticationViewModel.email else "no hay nada",
                fontSize = 30.sp,
                color = GoldDark
            )
            Text(
                text = "Contra: " + if (userAuthenticationViewModel.password != "") userAuthenticationViewModel.password else "no hay nada",
                fontSize = 30.sp,
                color = GoldDark
            )
            Text(
                text = "Token: " + if (userAuthenticationViewModel.token != null) userAuthenticationViewModel.token else "no hay nada",
                fontSize = 30.sp,
                color = GoldDark
            )
        }
    }
}