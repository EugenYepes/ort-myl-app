package com.ar.mylapp.utils

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine

//? Token
suspend fun refreshFirebaseToken(): String? {
    return suspendCancellableCoroutine { cont ->
        val user = FirebaseAuth.getInstance().currentUser
        if (user == null) {
            cont.resume(null, null)
        } else {
            user.getIdToken(true)
                .addOnSuccessListener { result ->
                    cont.resume(result.token, null)
                }
                .addOnFailureListener { e ->
                    cont.resume(null, null)
                }
        }
    }
}

//? Card Detail
fun capitalizeTitle(input: String): String {
    val exceptions = setOf("de", "la", "el", "los", "las", "y", "en", "del")
    return input
        .split(" ")
        .mapIndexed { index, word ->
            if (word.lowercase() in exceptions && index != 0) {
                word.lowercase()
            } else {
                word.lowercase().replaceFirstChar { it.titlecase() }
            }
        }
        .joinToString(" ")
}

enum class DialogType { ADD_SUCCESS, ADD_FAIL, DELETE_SUCCESS, DELETE_FAIL }