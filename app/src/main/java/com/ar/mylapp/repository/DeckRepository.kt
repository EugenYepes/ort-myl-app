package com.ar.mylapp.repository

import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.network.DeckRetrofit
import javax.inject.Inject

class DeckRepository @Inject constructor(
    private val deckRetrofit: DeckRetrofit
) {
    suspend fun addDeck(token: String, name: String, description: String) : DeckDTO {
        return deckRetrofit.addDeck(token, name, description)
    }

    suspend fun getDecks(token: String): List<DeckDTO> {
        return deckRetrofit.getDecks(token)
    }
}