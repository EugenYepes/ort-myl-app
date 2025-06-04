package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.Button5
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.entryData.MySearchBar
import com.ar.mylapp.navigation.Screens
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

    val title = stringResource(R.string.topbar_cards_title)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title)
    }

    val searchCardViewModel: SearchCardViewModel = hiltViewModel()
    val searchQuery = searchCardViewModel.searchQuery
    val foundCards = searchCardViewModel.foundCards

    val cards = viewModel.cards
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        ) {
            MySearchBar(
                placeholder = "Buscar carta por nombre...",
                searchQuery = searchCardViewModel.searchQuery,
                onValueChange = {
                    searchCardViewModel.updateQuery(it)
                }
            )
            Button5(
                onClick = { navController.navigate(Screens.AdvanceSearch.screen)},
                text = stringResource(R.string.advance_search)
            )
        }

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
