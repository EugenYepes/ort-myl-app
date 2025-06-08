package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.mylapp.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import users.PlayerDTO
import users.StoreDTO
import users.UserDTO
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    var updateError by mutableStateOf<String?>(null)
    var deleteSuccess by mutableStateOf(false)
    var updateSuccess by mutableStateOf(false)

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
}