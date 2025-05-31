package com.ar.mylapp.auth

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ar.mylapp.models.auth.StoreRegisterRequest
import com.ar.mylapp.models.auth.UserRegisterRequest
import com.ar.mylapp.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import users.PlayerDTO
import users.StoreDTO
import users.UserDTO
import javax.inject.Inject

@HiltViewModel
class UserAuthenticationViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

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
        //
        FirebaseAuthManager.login(
            email,
            password,
            onSuccess = { idToken ->
                token = idToken
                viewModelScope.launch {
                    val response = authRepository.login("Bearer $idToken")
                    if (response.isSuccessful) {
                        val loginData = response.body()
                        loginData?.let {
                            Log.d("LOGIN", "UUID: ${it.uuid}, Email: ${it.email}")
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        error = extractErrorMessage(errorBody)
                    }
                }
            },
            onError = {
                error = it
            }
        )
    }

    // La función onRegisterClicked ahora acepta un parámetro isStore
    // que indica si el registro es para un usuario normal o una tienda.
    fun onRegisterClicked(isStore: Boolean = false) {
        if (!validarCampos(isStore)) return

        // 1. Crear usuario en Firebase
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = FirebaseAuth.getInstance().currentUser
                val uid = user?.uid

                if (uid == null) {
                    error = "No se pudo obtener el UID de Firebase"
                    return@addOnSuccessListener
                }

                // 3. Registrar en el backend
                viewModelScope.launch {
                    val response = if (isStore) {
                        val storeRequest = StoreDTO().apply {
                            uuid = uid
                            email = email
                            name = storeName
                            phoneNumber = phone
                            address = address
                            isValid = false // or some default
                            url = "" // TODO
                        }
                        authRepository.registerStore(storeRequest)
                    } else {
                        val playerRequest = PlayerDTO().apply {
                            uuid = uid
                            email = email
                            name = "" // TODO
                        }
                        authRepository.registerUser(playerRequest)
                    }

                    if (response.isSuccessful) {
                        // 4. Enviar mail de verificación
                        user.sendEmailVerification()
                            ?.addOnSuccessListener {
                                FirebaseAuth.getInstance().signOut()
                                registrationSuccess = true
                                error = null
                            }
                            ?.addOnFailureListener { e ->
                                error = "No se pudo enviar el email: ${e.message}"
                            }
                    } else {
                        // 5. Si falla el backend, eliminar el usuario de Firebase
                        user.delete()
                            .addOnSuccessListener {
                                val errorMsg = extractErrorMessage(response.errorBody()?.string())
                                error = "Error al registrar en la base: $errorMsg"
                            }
                            .addOnFailureListener { deleteEx ->
                                error =
                                    "Backend falló y no se pudo eliminar el usuario Firebase: ${deleteEx.message}"
                            }
                    }
                }
            }
            .addOnFailureListener { e ->
                error = "Error al registrar usuario en Firebase: ${e.message}"
            }
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

    fun isLoggedIn(): Boolean {
        return this.token != null
    }

    fun resetRegistrationState() {
        registrationSuccess = false
    }

    private fun validarCampos(isStore: Boolean = false): Boolean {
        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
            error = "Completá email y contraseña"
            return false
        }

        if (password != confirmPassword) {
            error = "Las contraseñas no coinciden"
            return false
        }

        if (isStore && (storeName.isBlank() || address.isBlank() || phone.isBlank())) {
            error = "Completá los campos faltantes"
            return false
        }

        return true
    }

    private fun extractErrorMessage(json: String?): String {
        return try {
            val jsonObject = org.json.JSONObject(json ?: "")
            jsonObject.getString("error")
        } catch (e: Exception) {
            "Ocurrió un error inesperado"
        }
    }

}