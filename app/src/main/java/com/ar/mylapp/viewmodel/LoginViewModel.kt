package com.ar.mylapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ar.mylapp.auth.FirebaseAuthManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var error by mutableStateOf<String?>(null)
    var token by mutableStateOf<String?>(null)

    fun onLoginClicked() {
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
        FirebaseAuthManager.register(
            email,
            password,
            onSuccess = { error = null },
            onError = { error = it }
        )
    }
}