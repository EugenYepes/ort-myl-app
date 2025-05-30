package com.ar.mylapp.models.auth

import users.DeckDTO
import users.PlayerCardDTO

data class LoginResponse(
    val uuid: String,
    val email: String,
    val name: String?,
    val url: String? = null,
    val phoneNumber: String? = null,
    val address: String? = null,
    val isValid: Boolean? = null,
    val decks: List<DeckDTO>? = null,
    val cards: List<PlayerCardDTO>? = null
)