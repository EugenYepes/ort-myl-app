package com.ar.mylapp.network

import com.ar.mylapp.models.CardApi
import retrofit2.Response
import retrofit2.http.GET

interface CardApiService {
    @GET("getcards")
    suspend fun getCards(): Response<List<CardApi>>
}