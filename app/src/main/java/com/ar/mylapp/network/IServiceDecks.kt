package com.ar.mylapp.network

import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.models.cardProperties.DeckCardProperties

interface IServiceDecks {
    suspend fun addDeck(
        token: String,
        name: String,
        description: String
    ): DeckDTO

    suspend fun getDecks(token: String): List<DeckDTO>

    suspend fun addCardToDeck(token: String, cardId: Int, deckList: List<DeckCardProperties>): Boolean

    suspend fun deleteCardFromDeck(token: String, cardId: Int, deckList: List<DeckCardProperties>): Boolean
}