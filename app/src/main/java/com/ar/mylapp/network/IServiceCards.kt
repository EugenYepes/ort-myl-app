package com.ar.mylapp.network

import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.models.cardProperties.CardPropertiesDTO

interface IServiceCards {
    suspend fun getCards(
        currentPage: Int,
        pageSize: Int
    ): List<CardDTO>?

    suspend fun getFilteredCards(
        page: Int,
        pageSize: Int,
        filters: Map<String, List<Int>>
    ): List<CardDTO>

    suspend fun getFilterOptions(): Map<String, List<CardPropertiesDTO>>
}