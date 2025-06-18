package com.ar.mylapp.repository

import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.models.cardProperties.PlayerCardProperties
import com.ar.mylapp.models.cardProperties.CardPropertiesDTO
import com.ar.mylapp.models.cardProperties.PlayerCardRequest
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

    suspend fun searchCardsByName(name: String, page: Int = 1, pageSize: Int = 20): List<CardDTO>? = withContext(Dispatchers.IO) {
        cardRetrofit.searchCardsByName(name, page, pageSize)
    }

    suspend fun getUserCards(token: String, page: Int = 1, pageSize: Int = 10): List<PlayerCardProperties> {
        return cardRetrofit.getPlayerCards(token, page, pageSize)
    }

    suspend fun addPlayerCards(token: String, cards: List<PlayerCardRequest>): Boolean {
        return cardRetrofit.addPlayerCards(token, cards)
    }

    suspend fun deletePlayerCards(token: String, cards: List<PlayerCardRequest>): Boolean {
        return cardRetrofit.deletePlayerCards(token, cards)
    }
}