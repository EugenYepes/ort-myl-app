package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.ToggleViewButton
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.card.CardList
import com.ar.mylapp.components.entryData.MySearchBar
import com.ar.mylapp.components.text.Text1
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun UserCards(
    navController: NavController,
    cardViewModel: CardViewModel,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    topBarViewModel: TopBarViewModel
) {
    val title = stringResource(R.string.topbar_cards_title)
    val subtitle = stringResource(R.string.topbar_user_cards_subtitle)
    LaunchedEffect(Unit) { topBarViewModel.setTopBar(title, subtitle) }

    val token = userAuthenticationViewModel.token
    LaunchedEffect(token) {
        token?.let { cardViewModel.resetAndLoadUserCards(it) }
    }

    var isGridView by rememberSaveable { mutableStateOf(true) }
    var searchQuery by rememberSaveable { mutableStateOf("") }

    val allUserCards = cardViewModel.userCards
    val isLoading = cardViewModel.isLoading

    // Filtrar dentro de las cartas del usuario
    val displayedCards = if (searchQuery.isBlank()) {
        allUserCards
    } else {
        allUserCards.filter { it.card.name.contains(searchQuery, ignoreCase = true) }
    }

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

        if (!isLoading && displayedCards.isEmpty()) {
            Text1(stringResource(R.string.cards_not_found))
        }

        if (isGridView) {
            CardGrid(
                navController = navController,
                cards = displayedCards.map { it.card },
                isLoading = isLoading,
                onLoadMore = { token?.let { cardViewModel.loadMoreUserCards(it) } },
                enablePagination = true,
                showQuantityNumber = true,
                getQuantityForCard = { card ->
                    displayedCards.firstOrNull { it.card.id == card.id }?.quantity ?: 0
                }
            )
        } else {
            CardList(
                navController = navController,
                cards = displayedCards.map { it.card },
                isLoading = isLoading,
                onLoadMore = { token?.let { cardViewModel.loadMoreUserCards(it) } }
            )
        }
    }
}