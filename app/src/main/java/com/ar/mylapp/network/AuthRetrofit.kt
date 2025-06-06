package com.ar.mylapp.network

import ar.com.myldtos.users.PlayerDTO
import ar.com.myldtos.users.StoreDTO
import ar.com.myldtos.users.UserDTO
import retrofit2.Response
import javax.inject.Inject

class AuthRetrofit @Inject constructor(
    private val service: AuthApiService
) {

    suspend fun registerUser(request: PlayerDTO): Response<Unit> {
        return service.registerUser(request)
    }

    suspend fun registerStore(request: StoreDTO): Response<Unit> {
        return service.registerStore(request)
    }

    suspend fun login(token: String): Response<UserDTO> {
        return service.me(token)
    }

}