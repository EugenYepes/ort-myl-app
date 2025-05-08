package com.ar.mylapp.repository

import com.ar.mylapp.models.Card
import com.ar.mylapp.network.RetrofitInstance

class CardRepository {
    suspend fun fetchCards(): List<Card> {
        return RetrofitInstance.api.getCards()
    }
}