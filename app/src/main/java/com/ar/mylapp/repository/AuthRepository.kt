package com.ar.mylapp.repository

import ar.com.myldtos.users.PlayerDTO
import ar.com.myldtos.users.StoreDTO
import ar.com.myldtos.users.UserDTO
import com.ar.mylapp.network.AuthRetrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
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

    suspend fun deleteAccount(token: String): Response<Unit> = withContext(Dispatchers.IO) {
        authRetrofit.deleteAccount(token)
    }

    suspend fun updatePlayer(token: String, player: PlayerDTO): Response<Unit> = withContext(Dispatchers.IO) {
        authRetrofit.updatePlayer(token, player)
    }

    suspend fun updateStore(token: String, store: StoreDTO): Response<Unit> = withContext(Dispatchers.IO) {
        authRetrofit.updateStore(token, store)
    }

    suspend fun invalidateStore(token: String, storeUid: String): Response<Unit> = withContext(Dispatchers.IO) {
        authRetrofit.invalidateStore(token, storeUid)
    }
}