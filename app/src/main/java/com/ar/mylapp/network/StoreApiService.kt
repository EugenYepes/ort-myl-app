package com.ar.mylapp.network

import ar.com.myldtos.users.StoreDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface StoreApiService {
    @GET("stores")
    suspend fun getValidStores(@Query("valid") valid: Boolean = true): Response<List<StoreDTO>>
}