package com.ar.mylapp.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import users.StoreDTO
import users.UserDTO

interface AuthApiService {
    @POST("/api/auth/registerPlayer")
    suspend fun registerUser(@Body request: UserDTO): Response<Unit>

    @POST("/api/auth/registerStore")
    suspend fun registerStore(@Body request: StoreDTO): Response<Unit>

    @POST("/api/auth/login")
    suspend fun login(@Header("Authorization") token: String): Response<UserDTO>


}