package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.ToggleViewButton
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.card.CardList
import com.ar.mylapp.components.entryData.MySearchBar
import com.ar.mylapp.components.text.Text1
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.SearchCardViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun FilteredResultsScreen(
    navController: NavController,
    viewModel: CardViewModel,
    topBarViewModel: TopBarViewModel
) {
    val filteredCards = viewModel.filteredCards
    val isLoading = viewModel.isLoading
    val error = viewModel.errorMessage

    var isGridView by rememberSaveable { mutableStateOf(true) }
    var searchQuery by rememberSaveable { mutableStateOf("") }

    // Filtrar localmente dentro de filteredCards
    val displayedCards = if (searchQuery.isBlank()) {
        filteredCards
    } else {
        filteredCards.filter { it.name.contains(searchQuery, ignoreCase = true) }
    }

    val title = stringResource(R.string.topbar_cards_title)
    val subtitle = stringResource(R.string.topbar_advancesearch_subtitle)
    LaunchedEffect(Unit) { topBarViewModel.setTopBar(title, subtitle) }

    Column(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MySearchBar(
                    placeholder = stringResource(R.string.searchbar_placeholder),
                    searchQuery = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                ToggleViewButton(
                    isGridView = isGridView,
                    onClick = { isGridView = !isGridView }
                )
            }

            Spacer(modifier = Modifier.size(10.dp))
        }

        if (error != null) {
            Text(
                text = "Error: $error",
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }

        if (!isLoading && displayedCards.isEmpty()) {
            Text1(stringResource(R.string.cards_not_found))
        }

        if (isGridView) {
            CardGrid(
                navController = navController,
                cards = displayedCards,
                isLoading = isLoading,
                onLoadMore = { viewModel.loadMoreFilteredCards() }
            )
        } else {
            CardList(
                navController = navController,
                cards = displayedCards,
                isLoading = isLoading,
                onLoadMore = { viewModel.loadMoreFilteredCards() }
            )
        }
    }
}