package com.ar.mylapp.auth


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.myldtos.users.PlayerDTO
import ar.com.myldtos.users.StoreDTO
import androidx.navigation.NavController
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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
    var navigateToConfirmScreen by mutableStateOf(false)

    fun onLoginClicked(
        navController: NavController,
    ) {
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
                        navController.navigate(Screens.Home.screen) {
                            popUpTo(0) { inclusive = true }
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

        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                val user = firebaseAuth.currentUser
                val uid = user?.uid

                if (uid == null) {
                    error = "No se pudo obtener el UID de Firebase"
                    return@addOnSuccessListener
                }

                viewModelScope.launch {
                    try {
                        val response = if (isStore) {
                            Log.d("REGISTER", "UUID: $uid, Email: $email, Name: $storeName, phoneNumber: $phone, address: $address")
                            val storeRequest = StoreDTO()
                            storeRequest.uuid = uid
                            storeRequest.email = email
                            storeRequest.name = storeName
                            storeRequest.phoneNumber = phone
                            storeRequest.address = address
                            storeRequest.valid = true
                            storeRequest.url = ""

                            authRepository.registerStore(storeRequest)
                        } else {
                            Log.d("REGISTER", "UUID: $uid, Email: $email, Name: ''")
                            val playerRequest =  PlayerDTO()
                            playerRequest.uuid = uid
                            playerRequest.email = email
                            playerRequest.name = ""

                            authRepository.registerUser(playerRequest)
                        }

                        if (response.isSuccessful) {
                            user.sendEmailVerification()
                                .addOnSuccessListener {
                                    firebaseAuth.signOut()
                                    registrationSuccess = true
                                    navigateToConfirmScreen = true
                                    error = null
                                }
                                .addOnFailureListener { e ->
                                    error = "No se pudo enviar el email: ${e.message}"
                                }
                        } else {
                            user.delete()
                                .addOnSuccessListener {
                                    val errorMsg = extractErrorMessage(response.errorBody()?.string())
                                    error = "Error al registrar en la base: $errorMsg"
                                }
                                .addOnFailureListener { deleteEx ->
                                    error = "Backend falló y no se pudo eliminar el usuario Firebase: ${deleteEx.message}"
                                }
                        }

                    } catch (e: Exception) {
                        error = "Error inesperado: ${e.message}"
                        user.delete()
                    }
                }
            }
            .addOnFailureListener { e ->
                error = FirebaseAuthManager.getTranslatedErrorMessage(e)
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

        if (!isPasswordSecure(password)) {
            error = "La contraseña debe tener al menos 8 caracteres, un número y un símbolo"
            return false
        }

        if (isStore && (storeName.isBlank() || address.isBlank())) {
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
            "Ocurrió un error inesperado: ${e.message ?: "desconocido"}"
        }
    }

    fun isPasswordSecure(password: String): Boolean {
        val minLength = 8
        val hasDigit = password.any { it.isDigit() }
        val hasSymbol = password.any { !it.isLetterOrDigit() }
        return password.length >= minLength && hasDigit && hasSymbol
    }

}