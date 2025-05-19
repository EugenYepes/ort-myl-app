package com.ar.mylapp.network

import ar.com.myldtos.cards.CardDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CardApiService {
    //@GET("getcards")
    //suspend fun getCards(): Response<List<CardApi>>

    @GET("cards")
    suspend fun getCards(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<List<CardDTO>>
}