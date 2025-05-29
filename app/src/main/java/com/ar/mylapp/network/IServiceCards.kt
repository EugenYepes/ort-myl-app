package com.ar.mylapp.network

import ar.com.myldtos.cards.CardDTO

interface IServiceCards {
    suspend fun getCards(currentPage: Int, pageSize: Int): List<CardDTO>?

    //TODO suspend fun searchCards(name: String, page: Int, pageSize: Int): List<CardDTO>?

}