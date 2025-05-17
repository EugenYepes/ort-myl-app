package com.ar.mylapp.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserAuthenticationViewModel @Inject constructor() : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var error by mutableStateOf<String?>(null)
    var token by mutableStateOf<String?>(null)

    fun onLoginClicked() {
        if (email.isBlank() || password.isBlank()) {
            error = "Completá email y contraseña"
            return
        }
        FirebaseAuthManager.login(
            email,
            password,
            onSuccess = {
                token = it
                error = null
            },
            onError = {
                error = it
            }
        )
    }

    fun onRegisterClicked() {
        if (email.isBlank() || password.isBlank()) {
            error = "Completá email y contraseña"
            return
        }

        FirebaseAuthManager.register(
            email,
            password,
            onSuccess = { error = null },
            onError = { error = it }
        )
    }
}