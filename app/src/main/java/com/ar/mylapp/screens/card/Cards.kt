package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.entryData.MySearchBar
import com.ar.mylapp.ui.theme.Red
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.SearchCardViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun CardsScreen(
    navController: NavController,
    viewModel: CardViewModel = viewModel(),
    topBarViewModel: TopBarViewModel,
) {

    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("CARTAS")
    }

    val searchCardViewModel: SearchCardViewModel = viewModel()
    val searchQuery = searchCardViewModel.searchQuery
    val foundCards = searchCardViewModel.foundCards

    val cards = viewModel.cards
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column(modifier = Modifier.fillMaxSize()) {
        MySearchBar(
            placeholder = "Buscar carta por nombre...",
            searchQuery = searchCardViewModel.searchQuery,
            onValueChange = {
                searchCardViewModel.updateQuery(it, cards)
            }
        )

        if (error != null) {
            Text(
                text = "Error: $error",
                color = Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        val cardsToDisplay = if (searchQuery.isBlank()) cards else foundCards

        /*CardGrid(
            navController = navController,
            cards = cards,
            isLoading = isLoading,
            onLoadMore = { viewModel.loadMoreCards() }
        )*/
        CardGrid(
            navController = navController,
            cards = cardsToDisplay,
            isLoading = isLoading && searchQuery.isBlank(), // solo muestra loader si no se está buscando
            onLoadMore = { if (searchQuery.isBlank()) viewModel.loadMoreCards() }
        )
    }
}

