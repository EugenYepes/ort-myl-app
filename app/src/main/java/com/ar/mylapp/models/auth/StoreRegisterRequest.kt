package com.ar.mylapp.models.auth

data class StoreRegisterRequest(
    val uuid: String,
    val email: String,
    val password: String,
    val storeName: String,
    val address: String,
    val phone: String
)