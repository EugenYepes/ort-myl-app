package com.ar.mylapp.models.cardProperties

import ar.com.myldtos.cards.CardDTO

data class CardId(
    val id: Int
)

data class PlayerCardRequest(
    val card: CardId,
    val quantity: Int
)

data class PlayerCardProperties(
    val card: CardDTO,
    val quantity: Int
)