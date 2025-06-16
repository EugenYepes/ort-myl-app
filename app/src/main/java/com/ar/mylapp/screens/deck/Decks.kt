package com.ar.mylapp.screens.deck

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.deck.DeckNameCard
import com.ar.mylapp.components.popup.CreateDeckPopup
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun DecksScreen(
    navController: NavController,
    topBarViewModel: TopBarViewModel,
    decksViewModel: DecksViewModel,
    authViewModel: UserAuthenticationViewModel
){
    var showDialog by remember { mutableStateOf(false) }
    val decks by decksViewModel.decks

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp

    val titleSize = if (screenHeightDp < 700) 26.sp else 32.sp

    val title = stringResource(R.string.topbar_decks_title)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(decks) { deck ->
                DeckNameCard(
                    title2 = deck.name,
                    title3 = calculateTotalCards(deck),
                    modifier = Modifier.clickable {
                        navController.navigate(Screens.DeckDetail.withArgs(deck.id))
                    },
                    titleSize = titleSize
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button1(
            onClick = { showDialog = true },
            text = stringResource(R.string.new_deck),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }


    if (showDialog) {
        CreateDeckPopup(
            onDismiss = { showDialog = false },
            onConfirm = { name, description ->
                authViewModel.token?.let { token ->
                    decksViewModel.addDeck(
                        token = token,
                        name = name,
                        description = description,
                        onResult = { showDialog = false }
                    )
                }
            }
        )
    }
}

private fun calculateTotalCards(deck: DeckDTO): String {
    var total = 0
    deck.cards.forEach { card ->
        total += card.quantity
    }
    return total.toString()
}

