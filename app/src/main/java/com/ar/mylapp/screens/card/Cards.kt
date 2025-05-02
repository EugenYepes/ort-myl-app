package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.textComponent.Text4
import com.ar.mylapp.mock.sampleCards

@Composable
fun CardsScreen(navController: NavController){
    Column()
    {
        Text4("Buscador de cartas") //TODO Cambiar a una search bar
        CardGrid(navController, sampleCards)
    }
}