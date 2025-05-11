package com.ar.mylapp.repository

import com.ar.mylapp.network.CardRetrofit
import com.ar.mylapp.models.Card
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetServiceCardRepository @Inject constructor(
    private val cardRetrofit: CardRetrofit
) {
    suspend fun fetchCards(): List<Card>? = withContext(context = Dispatchers.IO) {
        cardRetrofit.getCards()
        //return RetrofitInstance.provideCardApiClient().getCards()
    }
}