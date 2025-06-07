package com.ar.mylapp.network

import android.util.Log
import users.StoreDTO
import javax.inject.Inject

class StoreRetrofit @Inject constructor(
    private val service: StoreApiService
) {
    suspend fun getValidStores(): List<StoreDTO> {
        val response = service.getValidStores()
        return if (response.isSuccessful) {
            response.body().orEmpty()
        } else {
            Log.e("StoreRetrofit", "Error: ${response.code()}")
            emptyList()
        }
    }
}