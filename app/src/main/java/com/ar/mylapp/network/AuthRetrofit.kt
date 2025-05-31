package com.ar.mylapp.network

//import com.ar.mylapp.models.auth.LoginResponse
//import com.ar.mylapp.models.auth.StoreRegisterRequest
//import com.ar.mylapp.models.auth.UserRegisterRequest
import retrofit2.Response
import users.PlayerDTO
import users.StoreDTO
import users.UserDTO
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
        return service.login(token)
    }

}