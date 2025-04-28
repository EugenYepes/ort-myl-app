package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ar.mylapp.components.buttons.ButtonPrimary
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.GoldDark

@Composable
fun CardsScreen(navController: NavController){
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
                text = "Cards Screen",
                fontSize = 30.sp,
                color = GoldDark
            )
            ButtonPrimary(onClick = { navController.navigate(Screens.CardDetail.screen) }, "Ir a detalle")
        }
    }
}