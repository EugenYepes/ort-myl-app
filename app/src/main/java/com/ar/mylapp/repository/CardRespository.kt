package com.ar.mylapp.repository

import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.network.CardRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetServiceCardRepository @Inject constructor(
    private val cardRetrofit: CardRetrofit
) {
    suspend fun fetchCards(): List<CardDTO>? = withContext(context = Dispatchers.IO) {
        cardRetrofit.getCards()
        //return RetrofitInstance.provideCardApiClient().getCards()
    }
}