package com.ar.mylapp.screens.account

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.auth.FirebaseAuthManager
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button4
import com.ar.mylapp.components.text.Text4
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.viewmodel.AccountViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AccountScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    topBarViewModel: TopBarViewModel,
    accountViewModel: AccountViewModel
) {
    val context = LocalContext.current

    var showDialog by remember { mutableStateOf(false) }
    var passwordInput by remember { mutableStateOf("") }
    var localError by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("MI CUENTA")
    }

    // Cuando se elimina la cuenta OK
    LaunchedEffect(accountViewModel.deleteSuccess) {
        if (accountViewModel.deleteSuccess) {
            userAuthenticationViewModel.clearSession()
            navController.navigate(Screens.Login.screen) {
                popUpTo("home") { inclusive = true }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(top = 32.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text4("Mail: ${userAuthenticationViewModel.email.ifBlank { "no hay nada" }}")
            Text4("Contra: ${userAuthenticationViewModel.password.ifBlank { "no hay nada" }}")

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    // Navegar a pantalla de edición
                }) {
                    Text("Actualizar Perfil")
                }

                Button(onClick = {
                    FirebaseAuthManager.logout(context)
                    userAuthenticationViewModel.clearSession()
                    navController.navigate(Screens.Login.screen) {
                        popUpTo("home") { inclusive = true }
                    }
                }) {
                    Text("Cerrar sesión")
                }
            }

            Button4(
                text = "Eliminar Cuenta",
                onClick = {
                    showDialog = true
                }
            )
        }

        // AlertDialog para confirmar eliminación con contraseña
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Confirmar eliminación") },
                text = {
                    Column {
                        Text("Para eliminar la cuenta, confirmá tu contraseña:")
                        OutlinedTextField(
                            value = passwordInput,
                            onValueChange = { passwordInput = it },
                            label = { Text("Contraseña") },
                            singleLine = true,
                            visualTransformation = PasswordVisualTransformation()
                        )
                        if (localError != null) {
                            Text(
                                text = localError ?: "",
                                color = Color.Red,
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                },
                confirmButton = {
                    TextButton(onClick = {
                        localError = null
                        val email = userAuthenticationViewModel.email
                        val password = passwordInput

                        if (email.isNotBlank() && password.isNotBlank()) {
                            FirebaseAuthManager.reAuthenticateFirebase(
                                email = email,
                                password = password,
                                onSuccess = {
                                    FirebaseAuth.getInstance().currentUser?.getIdToken(true)
                                        ?.addOnSuccessListener { tokenResult ->
                                            val token = tokenResult.token
                                            if (!token.isNullOrBlank()) {
                                                accountViewModel.deleteAccount("Bearer $token")
                                                showDialog = false
                                            } else {
                                                localError = "No se pudo obtener el token"
                                            }
                                        }
                                        ?.addOnFailureListener {
                                            localError = "Error al obtener el token: ${it.message}"
                                        }
                                },
                                onError = { errorMsg ->
                                    localError = errorMsg
                                }
                            )
                        } else {
                            localError = "Completá todos los campos"
                        }
                    }) {
                        Text("Eliminar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = {
                        showDialog = false
                        passwordInput = ""
                        localError = null
                    }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }
}