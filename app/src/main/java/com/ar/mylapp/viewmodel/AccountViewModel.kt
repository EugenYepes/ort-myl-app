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
import com.google.firebase.auth.FirebaseAuth
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

    fun getFullUserInfo(token: String) {
        viewModelScope.launch {
            isLoading = true

            val response = authRepository.login(token)
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

    fun updatePlayer(token: String, dto: PlayerDTO) {
        viewModelScope.launch {
            val response = authRepository.updatePlayer(token, dto)
            if (response.isSuccessful) {
                updateSuccess = true
                updateError = null
            } else {
                updateSuccess = false
                updateError = "No se pudo actualizar el jugador: ${response.errorBody()?.string()}"
            }
        }
    }

    fun updateStore(token: String, dto: StoreDTO) {
        viewModelScope.launch {
            val response = authRepository.updateStore(token, dto)
            if (response.isSuccessful) {
                updateSuccess = true
                updateError = null
            } else {
                updateSuccess = false
                updateError = "No se pudo actualizar la tienda: ${response.errorBody()?.string()}"
            }
        }
    }

    fun deleteAccount(token: String) {
        viewModelScope.launch {
            val response = authRepository.deleteAccount(token)
            deleteSuccess = response.isSuccessful
        }
    }

    fun deleteAccountWithPassword(email: String, password: String) {
        viewModelScope.launch {
            FirebaseAuthManager.reAuthenticateFirebase(
                email = email,
                password = password,
                onSuccess = {
                    val user = FirebaseAuth.getInstance().currentUser
                    user?.getIdToken(true)
                        ?.addOnSuccessListener { tokenResult ->
                            val token = tokenResult.token
                            if (!token.isNullOrBlank()) {
                                deleteAccount("Bearer $token")
                            } else {
                                updateError = "No se pudo obtener el token"
                            }
                        }
                        ?.addOnFailureListener {
                            updateError = "Error al obtener el token: ${it.message}"
                        }
                },
                onError = { errorMsg ->
                    updateError = errorMsg
                }
            )
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