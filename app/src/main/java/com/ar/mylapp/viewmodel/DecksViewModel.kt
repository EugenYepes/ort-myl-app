package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ar.mylapp.models.cardProperties.deck.Deck

class DecksViewModel : ViewModel() {
    var decks by mutableStateOf(listOf<Deck>())
        private set

    fun addDeck(name: String, description: String) {
        if (name.isNotBlank()) {
            val newId = (decks.maxOfOrNull { it.id } ?: 0) + 1
            val newDeck = Deck(
                id = newId,
                name = name,
                description = description,
                cards = emptyList()
            )
            decks = decks + newDeck
        }
    }
}