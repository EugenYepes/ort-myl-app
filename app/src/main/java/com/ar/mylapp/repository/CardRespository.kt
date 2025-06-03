package com.ar.mylapp.repository

import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.network.CardRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetServiceCardRepository @Inject constructor(
    private val cardRetrofit: CardRetrofit
) {
    suspend fun fetchCards(currentPage: Int, pageSize: Int): List<CardDTO>? = withContext(context = Dispatchers.IO) {
        cardRetrofit.getCards(currentPage, pageSize)
    }

    suspend fun searchCardsByName(name: String, page: Int = 1, pageSize: Int = 20): List<CardDTO>? = withContext(Dispatchers.IO) {
        cardRetrofit.searchCardsByName(name, page, pageSize)
    }

}