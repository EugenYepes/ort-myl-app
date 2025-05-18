package com.ar.mylapp.network

import ar.com.myldtos.cards.CardDTO
import javax.inject.Inject

class CardRetrofit
@Inject
constructor(private val service: CardApiService) : IServiceCards {

    override suspend fun getCards(currentPage: Int, pageSize: Int): List<CardDTO>? {
        //val response = service.getCards()

        val response = service.getCards(page = currentPage, pageSize = pageSize )

        return if (response.isSuccessful) {
            response.body()
        } else {
            emptyList()
        }
    }
}

