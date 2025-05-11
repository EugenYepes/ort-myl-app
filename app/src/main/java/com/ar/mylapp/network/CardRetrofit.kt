package com.ar.mylapp.network

import com.ar.mylapp.models.Card
import javax.inject.Inject

class CardRetrofit
@Inject
constructor(private val service: CardApiService) : IServiceCards {

    override suspend fun getCards(): List<Card>? {
        val response = service.getCards()

        return if (response.isSuccessful) {
            response.body()?.map { it.toCard() }
        } else {
            emptyList()
        }
    }
}

