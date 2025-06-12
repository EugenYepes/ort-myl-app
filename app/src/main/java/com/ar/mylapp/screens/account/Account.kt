package com.ar.mylapp.screens.account

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.auth.FirebaseAuthManager
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button4
import com.ar.mylapp.components.dialog.ConfirmDeleteDialog
import com.ar.mylapp.components.text.Text4
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.viewmodel.AccountViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel
import androidx.compose.ui.window.Dialog

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

    val localError by rememberUpdatedState(accountViewModel.updateError)

    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("MI CUENTA")
    }

    // cuando se borra la cuenta, redirige directo y muestra toast
    LaunchedEffect(accountViewModel.deleteSuccess) {
        if (accountViewModel.deleteSuccess) {
            userAuthenticationViewModel.clearSession()
            val toast = Toast.makeText(context, "Cuenta eliminada. Hasta la proxima!", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER, 0, 0)
            toast.show()
            navController.navigate(Screens.Login.screen) {
                popUpTo("home") { inclusive = true }
            }

            accountViewModel.deleteSuccess = false
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
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

        if (showDialog) {
            Dialog(onDismissRequest = {
                showDialog = false
                passwordInput = ""
                accountViewModel.updateError = null
            }) {
                ConfirmDeleteDialog(
                    title = "Confirmar eliminación",
                    description = "Para eliminar la cuenta, confirmá tu contraseña:",
                    password = passwordInput,
                    onPasswordChange = { passwordInput = it },
                    confirmButtonText = "Eliminar",
                    cancelButtonText = "Cancelar",
                    onConfirm = {
                        val email = userAuthenticationViewModel.email
                        val password = passwordInput

                        if (email.isNotBlank() && password.isNotBlank()) {
                            accountViewModel.deleteAccountWithPassword(email, password)
                        } else {
                            accountViewModel.updateError = "Completá todos los campos"
                        }
                    },
                    onCancel = {
                        showDialog = false
                        passwordInput = ""
                        accountViewModel.updateError = null
                    },
                    errorMessage = localError
                )
            }
        }
    }
}