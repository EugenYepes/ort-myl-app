package com.ar.mylapp.network

import com.ar.mylapp.models.Card

interface IServiceCards {
    suspend fun getCards(): List<Card>?
}