package com.ar.mylapp.network

import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.models.cardProperties.DeckProperties
import javax.inject.Inject

class DeckRetrofit @Inject constructor(
    private val service: DeckApiService
) : IServiceDecks {
    override suspend fun addDeck(token: String, name: String, description: String): DeckDTO {
        val request = DeckProperties(name, description)
        return service.addDeck(token, request)
    }

    override suspend fun getDecks(token: String): List<DeckDTO> {
        return service.getDecks(token)
    }

}