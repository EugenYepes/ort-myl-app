package com.ar.mylapp.network

import ar.com.myldtos.users.DeckDTO

interface IServiceDecks {
    suspend fun addDeck(
        token: String,
        name: String,
        description: String
    ): DeckDTO

    suspend fun getDecks(token: String): List<DeckDTO>
}