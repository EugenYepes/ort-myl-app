package com.ar.mylapp.auth

import android.content.Context
import com.google.firebase.auth.FirebaseAuth


// Signin con Google
/*
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
*/

object FirebaseAuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()


    fun register(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
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
    }

    fun login(email: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                auth.currentUser?.getIdToken(true)?.addOnSuccessListener { tokenResult ->
                    val idToken = tokenResult.token
                    if (idToken != null) onSuccess(idToken)
                    else onError("No se pudo obtener el token")
                }
            }
            .addOnFailureListener { onError(it.message ?: "Login fallido") }
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
            .addOnFailureListener { exception -> onError(exception.message ?: "Error al enviar el correo") }
    }



}