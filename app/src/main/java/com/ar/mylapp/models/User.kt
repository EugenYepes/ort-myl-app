package com.ar.mylapp.models

//TODO: Definir/Averiguar si se pone la contraseña o no
data class User (
    val userId: Int,
    val userName: String,
    val email: String,
    val phone: String,
    val decks: List<Deck>,
)