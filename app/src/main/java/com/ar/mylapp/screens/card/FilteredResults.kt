package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.text.Text1
import com.ar.mylapp.viewmodel.CardViewModel
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
    LaunchedEffect(Unit) { topBarViewModel.setTopBar(title, subtitle) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (error != null) {
            item {
                Text("Error: $error", color = Color.Red, modifier = Modifier.padding(8.dp))
            }
        }

        if (!isLoading && cards.isEmpty()) {
            item {
                Text1(stringResource(R.string.cards_not_found))
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 1000.dp) // Limita la altura para evitar constraints infinitos
            ) {
                CardGrid(
                    navController = navController,
                    cards = cards,
                    isLoading = isLoading,
                    onLoadMore = { viewModel.loadMoreFilteredCards() }
                )
            }
        }

    }
}
