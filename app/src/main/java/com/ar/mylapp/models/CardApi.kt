package com.ar.mylapp.models

import com.google.gson.annotations.SerializedName
import com.ar.mylapp.models.Collection

data class CardApi(
    @SerializedName(value = "cardId") val cardId: Int,
    @SerializedName(value = "imageUrl") val imageUrl: String,
    @SerializedName(value = "cardName") val cardName: String,
    @SerializedName(value = "ability") val ability: String?,
    @SerializedName(value = "cost") val cost: Int?,
    @SerializedName(value = "damage") val damage: Int?,
    @SerializedName(value = "collection") val collection: Collection,
    @SerializedName(value = "rarity") val rarity: Rarity?,
    @SerializedName(value = "type") val type: Type?,
    @SerializedName(value = "race") val race: Race?,
    @SerializedName(value = "formats") val formats: List<Format>,
    @SerializedName(value = "keyWords") val keyWords: List<KeyWord>
)



