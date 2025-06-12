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

    var userData by mutableStateOf<StoreDTO?>(null)

    fun getFullUserInfo(token: String) {
        viewModelScope.launch {
            val response = authRepository.getFullUserInfo(token)
            if (response.isSuccessful) {
                userData = response.body()
                updateError = null
            } else {
                updateError = "No se pudo obtener los datos del usuario"
            }
        }
    }

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

}