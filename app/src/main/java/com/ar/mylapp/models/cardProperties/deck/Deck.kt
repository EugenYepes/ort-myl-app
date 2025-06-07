package com.ar.mylapp.models.cardProperties.deck

data class Deck(
    val id: Int,
    val name: String,
    val description: String,
    val cards: List<Int>
)
