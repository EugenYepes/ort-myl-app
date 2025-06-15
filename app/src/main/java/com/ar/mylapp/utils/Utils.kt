package com.ar.mylapp.utils

import ar.com.myldtos.users.DeckDTO

// Stores
fun prepareUrl(rawUrl: String): String? {
    val trimmed = rawUrl.trim()
    if (trimmed.isBlank()) return null

    val fixed = if (!trimmed.startsWith("http://") && !trimmed.startsWith("https://")) {
        "https://$trimmed"
    } else trimmed

    val regex = Regex("^(https?://)?[\\w.-]+\\.[a-z]{2,}(/\\S*)?$", RegexOption.IGNORE_CASE)
    return if (regex.matches(fixed)) fixed else null
}

fun getDisplayUrl(rawUrl: String?): String {
    if (rawUrl.isNullOrBlank()) return ""
    return rawUrl
        .removePrefix("https://")
        .removePrefix("http://")
        .let {
            if (!it.startsWith("www.")) "www.$it" else it
        }
}

// Cards
fun capitalizeTitle(input: String): String {
    val exceptions = setOf("de", "la", "el", "los", "las", "y", "en", "del")
    return input
        .split(" ")
        .mapIndexed { index, word ->
            if (word.lowercase() in exceptions && index != 0) {
                word.lowercase()
            } else {
                word.lowercase().replaceFirstChar { it.titlecase() }
            }
        }
        .joinToString(" ")
}

enum class SuccessDialogType { ADD, DELETE }

// Decks
fun calculateTotalCards(deck: DeckDTO): String {
    var total = 0
    deck.cards.forEach { card ->
        total += card.quantity
    }
    return total.toString()
}