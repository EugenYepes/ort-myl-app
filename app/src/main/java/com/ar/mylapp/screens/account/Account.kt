package com.ar.mylapp.screens.account

import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
import ar.com.myldtos.users.PlayerDTO
import ar.com.myldtos.users.StoreDTO
import com.ar.mylapp.components.entryData.InputOne
import com.ar.mylapp.R
import com.ar.mylapp.navigation.prepareUrl

@Composable
fun AccountScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    topBarViewModel: TopBarViewModel,
    accountViewModel: AccountViewModel
) {
    var title = stringResource(R.string.topbar_account_title)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title)
        userAuthenticationViewModel.token?.let { token ->
            accountViewModel.clearUserData()
            accountViewModel.getFullUserInfo("Bearer $token")
        }
    }

    val context = LocalContext.current
    var showDialog by remember { mutableStateOf(false) }
    var passwordInput by remember { mutableStateOf("") }

    val localError by rememberUpdatedState(accountViewModel.updateError)
    val storeDto = accountViewModel.storeDTO
    val playerDto = accountViewModel.playerDTO

    var name by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var url by rememberSaveable { mutableStateOf("") }

    fun initializeUserFieldsIfEmpty(store: StoreDTO?, player: PlayerDTO?) {
        if (name.isBlank()) name = store?.name ?: player?.name ?: ""
        if (address.isBlank()) address = store?.address ?: ""
        if (phone.isBlank()) phone = store?.phoneNumber ?: ""
        if (url.isBlank()) url = store?.url ?: ""
    }

    LaunchedEffect(storeDto, playerDto) {
        initializeUserFieldsIfEmpty(storeDto, playerDto)
    }

    // Cuando se elimina la cuente
    LaunchedEffect(accountViewModel.deleteSuccess) {
        if (accountViewModel.deleteSuccess) {
            userAuthenticationViewModel.clearSession()
            Toast.makeText(context, "Cuenta eliminada. Hasta la próxima!", Toast.LENGTH_LONG).apply {
                setGravity(Gravity.CENTER, 0, 0)
                show()
            }
            navController.navigate(Screens.Login.screen) {
                popUpTo("home") { inclusive = true }
            }
            accountViewModel.deleteSuccess = false
        }
    }

    // Cuando se actualizan los datos
    LaunchedEffect(accountViewModel.updateSuccess) {
        if (accountViewModel.updateSuccess) {
            userAuthenticationViewModel.token?.let { token ->
                accountViewModel.getFullUserInfo("Bearer $token")
            }
            Toast.makeText(context, "Datos actualizados correctamente", Toast.LENGTH_SHORT).show()
            accountViewModel.updateSuccess = false
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

            if (!accountViewModel.isStoreUser() && !accountViewModel.isPlayerUser()) {
                Text4("Cargando datos del usuario...")
                return@Column
            }

            if (accountViewModel.isLoading) {
                CircularProgressIndicator()
                return@Column
            }

            InputOne(
                label = "Email",
                value = accountViewModel.storeDTO?.email ?: accountViewModel.playerDTO?.email ?: "",
                onValueChange = {},
                enabled = false
            )

            InputOne(
                label = "Nombre",
                value = name,
                onValueChange = { name = it }
            )

            if (accountViewModel.isStoreUser()) {
                InputOne("Dirección", address, { address = it })
                InputOne("Teléfono", phone, { phone = it })
                InputOne("URL", url, { url = it })
            }

            accountViewModel.updateError?.let { errorText ->
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    userAuthenticationViewModel.token?.let { token ->
                        val bearerToken = token
                        if (accountViewModel.isStoreUser()) {
                            if (address.isBlank()) {
                                accountViewModel.updateError = "La dirección no puede estar vacía"
                                return@Button
                            }

                            val preparedUrl = prepareUrl(url)
                            if (preparedUrl == null) {
                                accountViewModel.updateError = "Ingresá una URL válida (ej: www.tienda.com)"
                                return@Button
                            }

                            val store = accountViewModel.storeDTO!!
                            val updatedStore = StoreDTO().apply {
                                uuid = store.uuid
                                email = store.email
                                setName(name)
                                this.address = address
                                this.phoneNumber = phone
                                this.url = preparedUrl
                            }
                            accountViewModel.updateStore(bearerToken, updatedStore)
                        } else if (accountViewModel.isPlayerUser()) {
                            val player = accountViewModel.playerDTO!!
                            val updatedPlayer = PlayerDTO().apply {
                                uuid = player.uuid
                                email = player.email
                                setName(name)
                            }
                            accountViewModel.updatePlayer(bearerToken, updatedPlayer)
                        }
                    }
                }) {
                    Text("Actualizar Datos")
                }

                Button(onClick = {
                    FirebaseAuthManager.logout(context)
                    userAuthenticationViewModel.clearSession()
                    accountViewModel.clearUserData()
                    navController.navigate(Screens.Login.screen) {
                        popUpTo("home") { inclusive = true }
                    }
                }) {
                    Text("Cerrar sesión")
                }
            }

            Button4(
                text = "Eliminar Cuenta",
                onClick = { showDialog = true }
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