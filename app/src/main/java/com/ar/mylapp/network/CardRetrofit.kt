package com.ar.mylapp.network

import ar.com.myldtos.cards.CardDTO
import javax.inject.Inject

class CardRetrofit
@Inject
constructor(private val service: CardApiService) : IServiceCards {

    override suspend fun getCards(): List<CardDTO>? {
        //val response = service.getCards()

        val response = service.getCards(page = 5, pageSize = 5 )

        return if (response.isSuccessful) {
            response.body()
        } else {
            emptyList()
        }
    }
}

