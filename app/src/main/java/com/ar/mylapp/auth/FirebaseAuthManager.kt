package com.ar.mylapp.auth

import android.content.Context
import android.util.Log
import com.google.firebase.auth.FirebaseAuth


// Signin con Google
/*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
*/

object FirebaseAuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

//DEL REGISTER SE OCUPA EL BACKEND
  /*  fun register(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.sendEmailVerification()
                    ?.addOnSuccessListener {
                        onSuccess() // OK
                    }
                    ?.addOnFailureListener { except ->
                        onError("Usuario creado, pero no se pudo enviar el email de verificación: ${except.message}")
                    }
                }
                else onError(task.exception?.message ?: "Error desconocido al registrarse")
            }
    } */

    fun login(email: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val user = auth.currentUser
                if (user != null && user.isEmailVerified
                    ) {
                    user.getIdToken(true)
                        .addOnSuccessListener { tokenResult ->
                            val idToken = tokenResult.token
                            Log.d("AuthToken", "Token de ID: $idToken")
                            if (idToken != null) onSuccess(idToken)
                            else onError("No se pudo obtener el token")
                        }
                        .addOnFailureListener {
                            onError("Error al obtener el token: ${it.message}")
                            Log.d("LOGIN", "Error al obtener el token: ${it.message}")
                        }
                } else {
                    // Se hace un signout porque para verificar el email verificado
                    // primero tiene que conectarse
                    FirebaseAuth.getInstance().signOut()
                    onError("Debes verificar tu correo antes de ingresar. Revisa tu bandeja de entrada.")
                    Log.d("LOGIN", "Correo no verificado")
                }
            }
            .addOnFailureListener {
                onError(it.message ?: "Login Fallido")
                Log.d("LOGIN", ("Error en el Login -> " + it.message))
            }
    }

    fun logout(context: Context) {
        FirebaseAuth.getInstance().signOut()
        // Cerrar sesión exclusivamente de Google
        /* GoogleSignIn.getClient(
            context,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1055501727698-j0umm7kllbvpgoufnbvrnsfu1ap2mm5b.apps.googleusercontent.com")
                .requestEmail()
                .build()
        ).signOut() */
    }

    fun getCurrentUserEmail(): String? = auth.currentUser?.email

    fun resetPassword(
        email: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        auth.sendPasswordResetEmail(email)
            .addOnSuccessListener { onSuccess() }
            //.addOnFailureListener { exception -> onError(exception.message ?: "Error al enviar el correo") }
            .addOnFailureListener { exception -> onError(getTranslatedErrorMessage(exception)) }
    }

    // metodo necesario para borrar la cuenta
    // o cambiar de email
    fun reAuthenticateFirebase(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            onError("No hay usuario logueado.")
            return
        }

        val credential = com.google.firebase.auth.EmailAuthProvider.getCredential(email, password)
        user.reauthenticate(credential)
            .addOnSuccessListener {
                onSuccess() // se reautentica para despues borrar
            }
            .addOnFailureListener { ex ->
                onError(getTranslatedErrorMessage(ex))
            }
    }

    fun getTranslatedErrorMessage(exception: Exception): String {
        val errorCode = (exception as? com.google.firebase.auth.FirebaseAuthException)?.errorCode
        return when (errorCode) {
            "ERROR_INVALID_EMAIL" -> "El email no tiene un formato válido"
            "ERROR_USER_NOT_FOUND" -> "No existe una cuenta con este email"
            "ERROR_WRONG_PASSWORD" -> "La contraseña es incorrecta"
            "ERROR_EMAIL_ALREADY_IN_USE" -> "Ya hay una cuenta con este email"
            "ERROR_WEAK_PASSWORD" -> "La contraseña es demasiado débil (mínimo 6 caracteres)"
            "ERROR_USER_DISABLED" -> "Esta cuenta fue deshabilitada"
            "ERROR_TOO_MANY_REQUESTS" -> "Demasiados intentos fallidos. Intentá más tarde"
            "ERROR_OPERATION_NOT_ALLOWED" -> "Este tipo de autenticación no está habilitado"
            "ERROR_NETWORK_REQUEST_FAILED" -> "Error de red. Verificá tu conexión a Internet"
            "ERROR_INVALID_CREDENTIAL" -> "Las credenciales son inválidas o han expirado"
            else -> exception.message ?: "Ocurrió un error inesperado"
        }
    }

}