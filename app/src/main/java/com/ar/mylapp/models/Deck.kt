package com.ar.mylapp.models

data class Deck (
    val deckId: Int,
    val deckName: String,
    val cards: List<Card>,
    val description: String,
)