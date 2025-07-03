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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button6
import com.ar.mylapp.components.buttons.ToggleViewButton
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.card.CardList
import com.ar.mylapp.components.entryData.MySearchBar
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.Red
import com.ar.mylapp.viewmodel.AccountViewModel
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.SearchCardViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun CardsScreen(
    navController: NavController,
    cardViewModel: CardViewModel,
    topBarViewModel: TopBarViewModel,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    accountViewModel: AccountViewModel
) {
    val title = stringResource(R.string.topbar_cards_title)
    LaunchedEffect(Unit) { topBarViewModel.setTopBar(title) }
    var isGridView by rememberSaveable { mutableStateOf(true) }
    val searchCardViewModel: SearchCardViewModel = hiltViewModel()
    val searchQuery = searchCardViewModel.searchQuery
    val foundCards = searchCardViewModel.foundCards
    val cards = cardViewModel.cards
    val isLoading = cardViewModel.isLoading
    val error = cardViewModel.errorMessage

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MySearchBar(
                    placeholder = stringResource(R.string.searchbar_placeholder),
                    searchQuery = searchQuery,
                    onValueChange = { searchCardViewModel.updateQuery(it) },
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                ToggleViewButton(
                    isGridView = isGridView,
                    onClick = { isGridView = !isGridView }
                )
            }
            Spacer(modifier = Modifier.size(10.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally)
            ) {
                Button6(
                    onClick = { navController.navigate(Screens.AdvanceSearch.screen) },
                    icon = painterResource(id = R.drawable.filter_icon),
                    text = stringResource(R.string.advance_search)
                )
                Button6(
                    onClick = { navController.navigate(Screens.UserCards.screen) },
                    icon = painterResource(id = R.drawable.user_cards_icon),
                    text = stringResource(R.string.user_cards),
                    enabled = userAuthenticationViewModel.isLoggedIn() && accountViewModel.isPlayerUser()
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
        }

        if (error != null) {
            Text(
                text = error,
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

        if(isGridView){
            CardGrid(
                navController = navController,
                cards = cardsToDisplay,
                isLoading = if (searchQuery.isBlank()) isLoading else searchCardViewModel.isLoading,
                onLoadMore = { if (searchQuery.isBlank()) cardViewModel.loadMoreCards() }
            )
        } else {
            CardList(
                navController = navController,
                cards = cardsToDisplay,
                isLoading = if (searchQuery.isBlank()) isLoading else searchCardViewModel.isLoading,
                onLoadMore = { if (searchQuery.isBlank()) cardViewModel.loadMoreCards() }
            )
        }
    }
}