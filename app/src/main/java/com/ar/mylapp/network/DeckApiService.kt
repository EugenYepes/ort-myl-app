package com.ar.mylapp.network

import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.models.cardProperties.DeckCardProperties
import com.ar.mylapp.models.cardProperties.DeckProperties
import retrofit2.http.Body
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Header
import retrofit2.http.POST

interface DeckApiService {
    @POST("/api/player/deck")
    suspend fun addDeck(@Header("Authorization") token: String, @Body request: DeckProperties): DeckDTO

    @GET("/api/player/deck")
    suspend fun getDecks(@Header("Authorization") token: String): List<DeckDTO>

    @PUT("/api/player/deck/{id}")
    suspend fun updateDeck(@Header("Authorization") token: String, @Path("id") id: Int, @Body deck: DeckDTO): Response<Void>

    @DELETE("/api/player/deck/{id}")
    suspend fun deleteDeck(@Header("Authorization") token: String, @Path("id") id: Int): Response<Void>

    @POST("/api/player/deckcard/{cardId}")
    suspend fun addCardToDeck(
        @Header("Authorization") token: String,
        @Path("cardId") cardId: Int,
        @Body deckList: List<DeckCardProperties>
    ): Response<Void>

    @HTTP(method = "DELETE", path = "/api/player/deckcard/{cardId}", hasBody = true)
    suspend fun deleteCardFromDeck(
        @Header("Authorization") token: String,
        @Path("cardId") cardId: Int,
        @Body deckList: List<DeckCardProperties>
    ): Response<Void>
}