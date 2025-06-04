package com.ar.mylapp.screens.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ar.mylapp.components.buttons.Button2
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun StoresScreen(
    navController: NavController,
    topBarViewModel: TopBarViewModel
){
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("TIENDAS")
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
                text = "Stores Screen",
                fontSize = 30.sp,
                color = GoldDark
            )

            //Para testear deep link a WhatsApp. Reemplazar luego con lista de tiendas
            Button2(
                onClick = { navController.navigate(Screens.StoreDetail.screen)},
                text = "Comunicarme por WhatsApp",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(245.dp)
            )
        }
    }
}