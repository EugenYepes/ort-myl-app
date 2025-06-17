package com.ar.mylapp.network

import ar.com.myldtos.users.PlayerDTO
import ar.com.myldtos.users.StoreDTO
import ar.com.myldtos.users.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface AuthApiService {
    @POST("/api/players/register")
    suspend fun registerUser(@Body request: PlayerDTO): Response<Unit>

    @POST("/api/stores/register")
    suspend fun registerStore(@Body request: StoreDTO): Response<Unit>

    @GET("/api/auth/me")
    suspend fun me(@Header("Authorization") token: String): Response<UserDTO>

    @DELETE("/api/users")
    suspend fun deleteAccount(@Header("Authorization") token: String): Response<Unit>

    @PUT("/api/players")
    suspend fun updatePlayer(@Header("Authorization") token: String, @Body player: PlayerDTO
    ): Response<Unit>

    @PUT("/api/stores")
    suspend fun updateStore(@Header("Authorization") token: String, @Body store: StoreDTO
    ): Response<Unit>

    @PUT("/api/admin/stores/invalidate/{storeUid}")
    suspend fun invalidateStore(
        @Header("Authorization") token: String,
        @Path("storeUid") storeUid: String
    ): Response<Unit>
}