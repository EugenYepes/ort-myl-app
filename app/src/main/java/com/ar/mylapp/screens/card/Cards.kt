package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.text.Text4
import com.ar.mylapp.ui.theme.Red
import com.ar.mylapp.viewmodel.CardViewModel

@Composable
fun CardsScreen(
    navController: NavController,
    viewModel: CardViewModel = viewModel()
){
    val cards = viewModel.cards
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column()
    {
        Text4("Buscador de cartas", modifier = Modifier.padding(8.dp)) //TODO Cambiar a una search bar

        when {
            isLoading -> {
                CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
            }
            error != null -> {
                Text("Error: $error", color = Red)
            }
            else -> {
                CardGrid(navController, cards)
            }
        }
    }
}