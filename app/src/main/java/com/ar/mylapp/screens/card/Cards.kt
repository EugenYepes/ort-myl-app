package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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

    val searchCardViewModel: SearchCardViewModel = hiltViewModel()
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
                searchCardViewModel.updateQuery(it)
            }
        )

        if (error != null) {
            Text(
                text = "Error: $error",
                color = Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        if (searchCardViewModel.errorMessage != null) {
            Text(
                text = "Error: ${searchCardViewModel.errorMessage}",
                color = Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        val cardsToDisplay = if (searchQuery.isBlank()) cards else foundCards

        CardGrid(
            navController = navController,
            cards = cardsToDisplay,
            isLoading = if (searchQuery.isBlank()) isLoading else searchCardViewModel.isLoading,
            onLoadMore = { if (searchQuery.isBlank()) viewModel.loadMoreCards() }
        )
    }
}

