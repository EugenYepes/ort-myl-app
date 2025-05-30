package com.ar.mylapp.network

import com.ar.mylapp.models.auth.LoginRequest
import com.ar.mylapp.models.auth.LoginResponse
import com.ar.mylapp.models.auth.StoreRegisterRequest
import com.ar.mylapp.models.auth.UserRegisterRequest
import retrofit2.Response
import javax.inject.Inject

class AuthRetrofit @Inject constructor(
    private val service: AuthApiService
) {

    suspend fun registerUser(request: UserRegisterRequest): Response<Unit> {
        return service.registerUser(request)
    }

    suspend fun registerStore(request: StoreRegisterRequest): Response<Unit> {
        return service.registerStore(request)
    }

    suspend fun login(token: String): Response<LoginResponse> {
        return service.login(token)
    }

}