package com.ar.mylapp.network

import com.ar.mylapp.models.auth.LoginResponse
import com.ar.mylapp.models.auth.StoreRegisterRequest
import com.ar.mylapp.models.auth.UserRegisterRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApiService {
    @POST("/api/auth/registerPlayer")
    suspend fun registerUser(@Body request: UserRegisterRequest): Response<Unit>

    @POST("/api/auth/registerStore")
    suspend fun registerStore(@Body request: StoreRegisterRequest): Response<Unit>

    @POST("/api/auth/login")
    suspend fun login(@Header("Authorization") token: String): Response<LoginResponse>


}