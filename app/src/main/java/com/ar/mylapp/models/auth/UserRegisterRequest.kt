package com.ar.mylapp.models.auth

data class UserRegisterRequest(
    val uuid: String,
    val email: String,
    val password: String
)