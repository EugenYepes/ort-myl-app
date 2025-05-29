package com.ar.mylapp.repository

import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.models.cardProperties.CardPropertiesDTO
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

    suspend fun fetchFilteredCards(page: Int, pageSize: Int, filters: Map<String, List<Int>>): List<CardDTO> {
        return cardRetrofit.getFilteredCards(page, pageSize, filters)
    }

    suspend fun getFilterOptions(): Map<String, List<CardPropertiesDTO>> {
        return cardRetrofit.getFilterOptions()
    }

    suspend fun fetchCardById(id: Int): CardDTO {
        return cardRetrofit.getCardById(id)
    }
}