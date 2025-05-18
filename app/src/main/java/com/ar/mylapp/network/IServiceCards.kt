package com.ar.mylapp.network

import ar.com.myldtos.cards.CardDTO

interface IServiceCards {
    suspend fun getCards(currentPage: Int, pageSize: Int): List<CardDTO>?
}