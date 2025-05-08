package com.ar.mylapp.network

import com.ar.mylapp.models.Card
import retrofit2.http.GET

interface CardApiService {
    @GET("getcards")
    suspend fun getCards(): List<Card>
}