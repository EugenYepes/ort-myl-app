package com.ar.mylapp.models.cardProperties


data class DeckProperties(
    val name: String,
    val description: String
)

data class DeckCardProperties(
    val deckId: Int,
    val quantity: Int
)