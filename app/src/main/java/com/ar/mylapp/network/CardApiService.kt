package com.ar.mylapp.network

import ar.com.myldtos.cards.CardDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CardApiService {
    @GET("cards")
    suspend fun getCards(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<List<CardDTO>>

    //Búsqueda de cartas por nombre en CardsScreen
    @GET("card/search")
    suspend fun searchCards(
        @Query("name") name: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 20
    ): Response<List<CardDTO>>

}