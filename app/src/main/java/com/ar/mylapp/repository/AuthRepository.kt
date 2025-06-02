package com.ar.mylapp.repository

import com.ar.mylapp.network.AuthRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import users.PlayerDTO
import users.StoreDTO
import users.UserDTO
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val authRetrofit: AuthRetrofit
) {

    suspend fun registerUser(request: PlayerDTO): Response<Unit> = withContext(Dispatchers.IO) {
        authRetrofit.registerUser(request)
    }

    suspend fun registerStore(request: StoreDTO): Response<Unit> = withContext(Dispatchers.IO) {
        authRetrofit.registerStore(request)
    }

    suspend fun login(token: String): Response<UserDTO> = withContext(Dispatchers.IO) {
        authRetrofit.login(token)
    }

}