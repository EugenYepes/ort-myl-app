package com.ar.mylapp.screens.deck

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.components.text.Text3
import com.ar.mylapp.components.title.Title1
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun DeckDetailScreen(
    deckId: Int,
    topBarViewModel: TopBarViewModel,
    decksViewModel: DecksViewModel
) {
    val deck = decksViewModel.decks.find { it.id == deckId }

    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("DETALLE MAZO", deck?.name)
    }

    if (deck == null) {
        Text(
            text = "Mazo no encontrado",
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
            text = "Todavía no hay cartas.",
            fontSize = 14.sp,
            color = GoldDark
        )
    }
}
