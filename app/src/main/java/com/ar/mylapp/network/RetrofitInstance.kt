package com.ar.mylapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://mitosyleyendas.free.beeceptor.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: CardApiService = retrofit.create(CardApiService::class.java)
}