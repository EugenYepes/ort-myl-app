package com.ar.mylapp.mock

import androidx.annotation.DrawableRes

data class Collection(
    val collectionId: Int,
    val collectionName: String
)

data class Rarity(
    val rarityId: Int,
    val rarityName: String
)

data class Type(
    val typeId: Int,
    val typeName: String
)

data class Race(
    val raceId: Int,
    val raceName: String
)

data class Format(
    val formatId: Int,
    val formatName: String
)

data class KeyWord(
    val keyWordId: Int,
    val keyWordName: String,
    val definition: String
)

data class Card(
    val cardId: Int,
    @DrawableRes val imageResId: Int, // Imagen local
    val cardName: String,
    val ability: String?,
    val cost: Int?,
    val damage: Int?,
    val collection: Collection,
    val rarity: Rarity?,
    val type: Type?,
    val race: Race?,
    val formats: List<Format>,
    val keyWords: List<KeyWord>
)