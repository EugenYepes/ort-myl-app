package com.ar.mylapp.repository

import com.ar.mylapp.models.auth.LoginRequest
import com.ar.mylapp.models.auth.LoginResponse
import com.ar.mylapp.models.auth.StoreRegisterRequest
import com.ar.mylapp.models.auth.UserRegisterRequest
import com.ar.mylapp.network.AuthRetrofit
import com.google.android.play.integrity.internal.a
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import users.PlayerDTO
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRetrofit: AuthRetrofit
) {
    suspend fun registerUser(request: UserRegisterRequest): Response<Unit> = withContext(Dispatchers.IO) {
        authRetrofit.registerUser(request)
    }

    suspend fun registerStore(request: StoreRegisterRequest): Response<Unit> = withContext(Dispatchers.IO) {
        authRetrofit.registerStore(request)
    }

    suspend fun login(token: String): Response<LoginResponse> = withContext(Dispatchers.IO) {
        authRetrofit.login(token)
    }

}