package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.myldtos.users.PlayerDTO
import ar.com.myldtos.users.StoreDTO
import com.ar.mylapp.auth.FirebaseAuthManager
import com.ar.mylapp.repository.AuthRepository
import com.ar.mylapp.utils.refreshFirebaseToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var updateError by mutableStateOf<String?>(null)
    var deleteSuccess by mutableStateOf(false)
    var updateSuccess by mutableStateOf(false)
    var isLoading by mutableStateOf(false)
    var storeDTO by mutableStateOf<StoreDTO?>(null)
    var playerDTO by mutableStateOf<PlayerDTO?>(null)

    fun getFullUserInfo() {
        viewModelScope.launch {
            isLoading = true

            val refreshedToken = refreshFirebaseToken()
            if (refreshedToken == null) {
                updateError = "No se pudo renovar el token"
                isLoading = false
                return@launch
            }

            val response = authRepository.login("Bearer $refreshedToken")
            if (response.isSuccessful) {
                playerDTO = null
                storeDTO = null

                val body = response.body()
                if (body is PlayerDTO) {
                    playerDTO = body
                } else if (body is StoreDTO) {
                    storeDTO = body
                }
                updateError = null
            } else {
                updateError = "No se pudo obtener los datos del usuario"
            }
            isLoading = false
        }
    }

    fun isStoreUser(): Boolean = storeDTO != null
    fun isPlayerUser(): Boolean = playerDTO != null

    fun updatePlayer(dto: PlayerDTO) {
        viewModelScope.launch {
            val token = refreshFirebaseToken()
            if (token != null) {
                val response = authRepository.updatePlayer(token, dto)
                updateSuccess = response.isSuccessful
                updateError = if (!response.isSuccessful)
                    "No se pudo actualizar el jugador: ${response.errorBody()?.string()}"
                else null
            } else {
                updateSuccess = false
                updateError = "No se pudo renovar el token"
            }
        }
    }

    fun updateStore(dto: StoreDTO) {
        viewModelScope.launch {
            val token = refreshFirebaseToken()
            if (token != null) {
                val response = authRepository.updateStore(token, dto)
                updateSuccess = response.isSuccessful
                updateError = if (!response.isSuccessful)
                    "No se pudo actualizar la tienda: ${response.errorBody()?.string()}"
                else null
            } else {
                updateSuccess = false
                updateError = "No se pudo renovar el token"
            }
        }
    }

    fun deleteAccount() {
        viewModelScope.launch {
            val token = refreshFirebaseToken()
            if (token != null) {
                val response = authRepository.deleteAccount("Bearer $token")
                deleteSuccess = response.isSuccessful
            } else {
                deleteSuccess = false
                updateError = "No se pudo renovar el token para eliminar la cuenta"
            }
        }
    }

    fun deleteAccountWithPassword(email: String, password: String) {
        viewModelScope.launch {
            val reAuthSuccess = FirebaseAuthManager.reAuthenticateFirebaseSuspend(email, password)
            if (reAuthSuccess) {
                deleteAccount()
            } else {
                updateError = "No se pudo reautenticar al usuario"
            }
        }
    }

    fun clearUserData() {
        storeDTO = null
        playerDTO = null
        updateError = null
        updateSuccess = false
        deleteSuccess = false
        isLoading = false
    }
}