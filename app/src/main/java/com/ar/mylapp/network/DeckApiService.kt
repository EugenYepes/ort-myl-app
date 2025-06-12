package com.ar.mylapp.network

import ar.com.myldtos.users.DeckDTO
import com.ar.mylapp.models.cardProperties.DeckProperties
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface DeckApiService {
    @POST("/api/player/deck")
    suspend fun addDeck(@Header("Authorization") token: String, @Body request: DeckProperties): DeckDTO

    @GET("/api/player/deck")
    suspend fun getDecks(@Header("Authorization") token: String): List<DeckDTO>
}