package com.ar.mylapp.screens.card

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun UserCards(
    navController: NavController,
    cardViewModel: CardViewModel,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    topBarViewModel: TopBarViewModel
){
    val title = stringResource(R.string.topbar_cards_title)
    val subtitle = stringResource(R.string.topbar_user_cards_subtitle)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title, subtitle)
    }

    val token = userAuthenticationViewModel.token
    LaunchedEffect(token) {
        token?.let {
            cardViewModel.resetAndLoadUserCards(it)
        }
    }

    CardGrid(
        navController = navController,
        cards = cardViewModel.userCards.map { it.card },
        isLoading = cardViewModel.isLoading,
        onLoadMore = {
            token?.let {
                cardViewModel.loadMoreUserCards(it)
            }
        },
        enablePagination = true,
        showQuantityNumber = true,
        getQuantityForCard = { card ->
            cardViewModel.userCards.firstOrNull { it.card.id == card.id }?.quantity ?: 0
        }
    )
}