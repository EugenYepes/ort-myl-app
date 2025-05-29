package com.ar.mylapp.repository

import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.network.CardRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetServiceCardRepository @Inject constructor(
    private val cardRetrofit: CardRetrofit
) {
    suspend fun fetchCards(currentPage: Int, pageSize: Int): List<CardDTO>? = withContext(context = Dispatchers.IO) {
        cardRetrofit.getCards(currentPage, pageSize)
        //return RetrofitInstance.provideCardApiClient().getCards()
    }

    //TODO
    /*suspend fun searchCards(name: String, page: Int, pageSize: Int): List<CardDTO>? =
        cardRetrofit.searchCards(name, page, pageSize)*/

}