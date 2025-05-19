package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ar.mylapp.components.tittle.Tittle1
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.components.card.CardDetailImage
import com.ar.mylapp.components.card.CardDetailPopup
import com.ar.mylapp.components.card.ShowButtons


@Composable
fun CardDetail(card: CardDTO) {
    var showPopup by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(22.dp))
            Tittle1(capitalizeTitle(card.name))
            Spacer(modifier = Modifier.size(22.dp))
            CardDetailImage(card)
            Spacer(modifier = Modifier.size(22.dp))
            ShowButtons(onClick = { showPopup = true })
            if (showPopup) {
                CardDetailPopup(onDismiss = { showPopup = false }, card)
            }
        }
    }
}

fun capitalizeTitle(input: String): String {
    val exceptions = setOf("de", "la", "el", "los", "las", "y", "en", "del")
    return input
        .split(" ")
        .mapIndexed { index, word ->
            if (word.lowercase() in exceptions && index != 0) {
                word.lowercase()
            } else {
                word.lowercase().replaceFirstChar { it.titlecase() }
            }
        }
        .joinToString(" ")
}
