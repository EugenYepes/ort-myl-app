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
    var confirmPassword by mutableStateOf("")
    var storeName by mutableStateOf("")
    var address by mutableStateOf("")
    var phone by mutableStateOf("")
    var registrationSuccess by mutableStateOf(false)
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

    // La función onRegisterClicked ahora acepta un parámetro isStore
    // que indica si el registro es para un usuario normal o una tienda.
    fun onRegisterClicked(isStore: Boolean = false) {
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            error = "Completá email y contraseña"
            return
        }

        if (password != confirmPassword) {
            error = "Las contraseñas no coinciden"
            return
        }

        // Validaciones adicionales para tiendas
        if (isStore) {
            if (storeName.isBlank() || address.isBlank() || phone.isBlank()) {
                error = "Completá los campos faltantes"
                return
            }
        }

        FirebaseAuthManager.register(
            email,
            password,
            onSuccess = {

                registrationSuccess = true
                error = null

                // Logica para enviar datos extras de tienda al backend
            },
            onError = { error = it }
        )
    }

    fun onResetPasswordClicked(onSuccess: () -> Unit) {
        if (email.isBlank()) {
            error = "Por favor ingresá un email"
            return
        }

        FirebaseAuthManager.resetPassword(
            email = email,
            onSuccess = {
                error = null
                onSuccess()
            },
            onError = {
                error = it
            }
        )
    }


    fun clearSession() {
        email = ""
        password = ""
        token = null
        error = null
    }

}