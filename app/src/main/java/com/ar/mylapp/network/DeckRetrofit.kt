package com.ar.mylapp.network

import android.util.Log
import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.models.cardProperties.DeckCardProperties
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

    suspend fun updateDeck(token: String, id: Int, name: String, description: String): Boolean {
        return try {
            val deckDTO = DeckDTO().apply {
                this.id = id
                this.name = name
                this.description = description
            }
            val response = service.updateDeck(token, id, deckDTO)
            response.isSuccessful
        } catch (e: Exception) {
            println("Error actualizando mazo: ${e.message}")
            false
        }
    }

    suspend fun deleteDeck(token: String, id: Int): Boolean {
        return try {
            val response = service.deleteDeck(token, id)
            response.isSuccessful
        } catch (e: Exception) {
            println("Error al eliminar el mazo: ${e.message}")
            false
        }
    }

    override suspend fun addCardToDeck(token: String, cardId: Int, deckList: List<DeckCardProperties>): Boolean {
        return try {
            val response = service.addCardToDeck(token, cardId, deckList)
            response.isSuccessful
        } catch (e: Exception) {
            println("Error al agregar carta a mazos: ${e.message}")
            false
        }
    }

    override suspend fun deleteCardFromDeck(token: String, cardId: Int, deckList: List<DeckCardProperties>): Boolean {
        return try {
            Log.d("DEBUG_DELETE", "Petición DELETE a /api/player/deckcard/$cardId")
            deckList.forEach {
                Log.d("DEBUG_DELETE", "DeckID: ${it.deckId}, Quantity: ${it.quantity}")
            }

            val response = service.deleteCardFromDeck(token, cardId, deckList)
            Log.d("DEBUG_DELETE", "Código de respuesta: ${response.code()}")
            response.isSuccessful
        } catch (e: Exception) {
            Log.e("DEBUG_DELETE", "Error al eliminar carta de los mazos: ${e.message}")
            false
        }
    }
}