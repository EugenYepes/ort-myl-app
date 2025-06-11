package com.ar.mylapp.network

import ar.com.myldtos.users.PlayerDTO
import ar.com.myldtos.users.StoreDTO
import ar.com.myldtos.users.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {
    @POST("/api/players/register")
    suspend fun registerUser(@Body request: PlayerDTO): Response<Unit>

    @POST("/api/stores/register")
    suspend fun registerStore(@Body request: StoreDTO): Response<Unit>

    @POST("/api/auth/me")
    suspend fun me(@Header("Authorization") token: String): Response<UserDTO>
}