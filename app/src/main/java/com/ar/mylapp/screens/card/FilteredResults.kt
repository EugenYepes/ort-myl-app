package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.R
import com.ar.mylapp.components.text.Text1
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun FilteredResultsScreen(
    navController: NavController,
    viewModel: CardViewModel,
    topBarViewModel: TopBarViewModel
) {
    val cards = viewModel.filteredCards
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    val title = stringResource(R.string.topbar_cards_title)
    val subtitle = stringResource(R.string.topbar_advancesearch_subtitle)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title, subtitle)
    }

    Column(Modifier.fillMaxSize()) {
        if (error != null) {
            Text("Error: $error", color = Color.Red, modifier = Modifier.padding(8.dp))
        }

        if (!isLoading && cards.isEmpty()) {
            Text1(stringResource(R.string.cards_not_found))
        }

        CardGrid(
            navController = navController,
            cards = cards,
            isLoading = isLoading,
            onLoadMore = { viewModel.loadMoreFilteredCards() }
        )
    }
}