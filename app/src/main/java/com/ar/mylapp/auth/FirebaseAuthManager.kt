package com.ar.mylapp.auth

import com.google.firebase.auth.FirebaseAuth

object FirebaseAuthManager {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    fun register(email: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) onSuccess()
                else onError(task.exception?.message ?: "Error desconocido")
            }
    }

    fun login(email: String, password: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                auth.currentUser?.getIdToken(true)?.addOnSuccessListener { result ->
                    val token = result.token
                    onSuccess(token ?: "")
                }
            }
            .addOnFailureListener { onError(it.message ?: "Login fallido") }
    }

    fun logout() {
        auth.signOut()
    }

    fun getCurrentUserEmail(): String? = auth.currentUser?.email
}