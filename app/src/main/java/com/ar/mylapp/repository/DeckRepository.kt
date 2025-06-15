package com.ar.mylapp.repository

import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.models.cardProperties.DeckCardProperties
import com.ar.mylapp.network.DeckRetrofit
import javax.inject.Inject

class DeckRepository @Inject constructor(
    private val deckRetrofit: DeckRetrofit
) {
    suspend fun addDeck(token: String, name: String, description: String): DeckDTO {
        return deckRetrofit.addDeck(token, name, description)
    }

    suspend fun getDecks(token: String): List<DeckDTO> {
        return deckRetrofit.getDecks(token)
    }

    suspend fun updateDeck(token: String, id: Int, name: String, description: String): Boolean {
        return deckRetrofit.updateDeck(token, id, name, description)
    }

    suspend fun deleteDeck(token: String, id: Int): Boolean {
        return deckRetrofit.deleteDeck(token, id)
    }

    suspend fun addCardToDeck(token: String, cardId: Int, deckList: List<DeckCardProperties>): Boolean {
        return deckRetrofit.addCardToDeck(token, cardId, deckList)
    }

    suspend fun deleteCardFromDeck(token: String, cardId: Int, deckList: List<DeckCardProperties>): Boolean {
        return deckRetrofit.deleteCardFromDeck(token, cardId, deckList)
    }
}
