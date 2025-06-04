package com.ar.mylapp.repository

import com.ar.mylapp.network.StoreRetrofit
import users.StoreDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StoreRepository @Inject constructor(
    private val storeRetrofit: StoreRetrofit
) {
    suspend fun fetchValidStores(): List<StoreDTO> = withContext(Dispatchers.IO) {
        storeRetrofit.getValidStores()
    }
}