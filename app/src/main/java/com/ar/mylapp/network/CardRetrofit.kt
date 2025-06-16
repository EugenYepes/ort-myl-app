package com.ar.mylapp.network

import android.util.Log
import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.models.cardProperties.CardPropertiesDTO
import com.ar.mylapp.models.cardProperties.PlayerCardProperties
import com.ar.mylapp.models.cardProperties.PlayerCardRequest
import javax.inject.Inject

class CardRetrofit @Inject constructor(
    private val service: CardApiService
) : IServiceCards {

    override suspend fun getCards(currentPage: Int, pageSize: Int): List<CardDTO>? {

        val response = service.getCards(page = currentPage, pageSize = pageSize)
        return if (response.isSuccessful) {
            response.body()
        } else {
            emptyList()
        }
    }

    override suspend fun searchCardsByName(name: String, page: Int, pageSize: Int): List<CardDTO>? {
        val response = service.searchCards(name = name, page = page, pageSize = pageSize)
        return if (response.isSuccessful) {
            response.body()
        } else {
            emptyList()
        }
    }

    suspend fun getCardById(id: Int): CardDTO {
        val response = service.getCardById(id)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Carta vacía")
        } else {
            throw Exception("Error al obtener la carta: ${response.code()}")
        }
    }

    override suspend fun getFilteredCards(
        page: Int,
        pageSize: Int,
        filters: Map<String, List<Int>>
    ): List<CardDTO> {
        val typeIds = filters["Tipos"]
        val rarityIds = filters["Rarezas"]
        val raceIds = filters["Razas"]
        val formatIds = filters["Formatos"]
        val collectionIds = filters["Colecciones"]
        val keywordIds = filters["Palabras clave"]

        Log.d("CardRetrofit", "Enviando filtros: typeId=$typeIds, rarityId=$rarityIds, ...")

        val response = service.getFilteredCards(
            page = page,
            pageSize = pageSize,
            typeIds = typeIds,
            rarityIds = rarityIds,
            raceIds = raceIds,
            formatIds = formatIds,
            collectionIds = collectionIds,
            keywordIds = keywordIds
        )

        return if (response.isSuccessful) response.body().orEmpty() else emptyList()
    }

    override suspend fun getFilterOptions(): Map<String, List<CardPropertiesDTO>> {
        return mapOf(
            "Tipos" to service.getTypes(),
            "Rarezas" to service.getRarities(),
            "Razas" to service.getRaces(),
            "Formatos" to service.getFormats(),
            "Colecciones" to service.getCollections(),
            "Palabras clave" to service.getKeywords()
        )
    }

    suspend fun getPlayerCards(token: String, page: Int, pageSize: Int): List<PlayerCardProperties> {
        val response = service.getPlayerCards(token, page, pageSize)
        if (response.isSuccessful) return response.body().orEmpty()
        else throw Exception("Error al obtener cartas del jugador: ${response.code()}")
    }

    suspend fun addPlayerCards(token: String, cards: List<PlayerCardRequest>): Boolean {
        val response = service.addPlayerCards(token, cards)
        return response.isSuccessful
    }

    suspend fun deletePlayerCards(token: String, cards: List<PlayerCardRequest>): Boolean {
        val response = service.deletePlayerCards(token, cards)
        return response.isSuccessful
    }
}

