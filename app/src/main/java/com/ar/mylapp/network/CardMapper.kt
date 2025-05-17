package com.ar.mylapp.network

import com.ar.mylapp.models.Card
import com.ar.mylapp.models.CardApi

fun CardApi.toCard(): Card {
    return Card(
        cardId = cardId,
        imageUrl = imageUrl,
        cardName = cardName,
        ability = ability,
        cost = cost,
        damage = damage,
        collection = collection,
        rarity = rarity,
        type = type,
        race = race,
        formats = formats,
        keyWords = keyWords
    )
}


