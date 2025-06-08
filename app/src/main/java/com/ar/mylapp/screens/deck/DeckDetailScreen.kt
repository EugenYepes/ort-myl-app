package com.ar.mylapp.screens.deck

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.components.text.Text3
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun DeckDetailScreen(
    deckId: Int,
    topBarViewModel: TopBarViewModel,
    decksViewModel: DecksViewModel
) {
    val deck = decksViewModel.decks.find { it.id == deckId }

    val title = stringResource(R.string.topbar_deck_details)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title, deck?.name)
    }

    if (deck == null) {
        Text(
            text = stringResource(R.string.deck_not_found),
            modifier = Modifier.padding(24.dp),
            fontSize = 16.sp,
            color = GoldDark
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text3(
            text = if (deck.description.isNotBlank()) deck.description else "Sin descripción",
            fontSize = 14.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth(),
        )

        Text(
            text = stringResource(R.string.no_cards),
            fontSize = 14.sp,
            color = GoldDark
        )
    }
}
