package com.ar.mylapp.models.auth

data class LoginRequest(
    val email: String,
    val password: String
)