package com.ar.mylapp.screens.account

import android.annotation.SuppressLint
import android.view.Gravity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import ar.com.myldtos.users.PlayerDTO
import ar.com.myldtos.users.StoreDTO
import com.ar.mylapp.R
import com.ar.mylapp.auth.FirebaseAuthManager
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button4
import com.ar.mylapp.components.buttons.Button6
import com.ar.mylapp.components.dialog.ConfirmDeleteDialog
import com.ar.mylapp.components.entryData.InputOne
import com.ar.mylapp.components.text.Text4
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.navigation.prepareUrl
import com.ar.mylapp.viewmodel.AccountViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun AccountScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    topBarViewModel: TopBarViewModel,
    accountViewModel: AccountViewModel
) {
    val title = stringResource(R.string.topbar_account_title)
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

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp
    val buttonWidth = if (screenHeightDp < 700) 150.dp else 180.dp

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
            Toast.makeText(
                context,
                context.getString(R.string.delete_account_toast),
                Toast.LENGTH_LONG
            ).apply {
                setGravity(Gravity.CENTER, 0, 0)
                show()
            }
            navController.navigate(Screens.Login.screen) {
                popUpTo(Screens.Home.screen) { inclusive = true }
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
            Toast.makeText(
                context,
                context.getString(R.string.update_info_toast), Toast.LENGTH_SHORT
            ).show()
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
                Text4(stringResource(R.string.loading_info))
                return@Column
            }

            if (accountViewModel.isLoading) {
                CircularProgressIndicator()
                return@Column
            }

            InputOne(
                label = stringResource(R.string.email),
                value = accountViewModel.storeDTO?.email ?: accountViewModel.playerDTO?.email ?: "",
                onValueChange = {},
                enabled = false
            )

            InputOne(
                label = stringResource(R.string.account_name),
                value = name,
                onValueChange = { name = it }
            )

            if (accountViewModel.isStoreUser()) {
                InputOne(stringResource(R.string.store_adress), address, { address = it })
                InputOne(stringResource(R.string.store_phone), phone, { phone = it })
                InputOne(stringResource(R.string.store_url), url, { url = it })
            }

            accountViewModel.updateError?.let { errorText ->
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button6(
                    text = stringResource(R.string.update_account),
                    icon = painterResource(id = R.drawable.account_edit_icon),
                    modifier = Modifier.width(buttonWidth),
                    onClick = {
                        userAuthenticationViewModel.token?.let { token ->
                            val bearerToken = token
                            if (accountViewModel.isStoreUser()) {
                                if (address.isBlank()) {
                                    accountViewModel.updateError =
                                        context.getString(R.string.update_error_adress)
                                    return@let
                                }
                                val preparedUrl = prepareUrl(url)
                                if (preparedUrl == null) {
                                    accountViewModel.updateError =
                                        context.getString(R.string.update_error_url)
                                    return@let
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
                    }
                )
                Spacer(modifier = Modifier.size(8.dp))
                Button6(
                    text = stringResource(R.string.sign_out),
                    modifier = Modifier.width(buttonWidth),
                    icon = painterResource(id = R.drawable.logout_icon),
                    onClick = {
                        FirebaseAuthManager.logout()
                        userAuthenticationViewModel.clearSession()
                        accountViewModel.clearUserData()
                        navController.navigate(Screens.Login.screen) {
                            popUpTo(Screens.Home.screen) { inclusive = true }
                        }
                    }
                )
            }

            Button4(
                text = stringResource(R.string.delete_account),
                onClick = { showDialog = true }
            )

            if (showDialog) {
                Dialog(onDismissRequest = {
                    showDialog = false
                    passwordInput = ""
                    accountViewModel.updateError = null
                }) {
                    ConfirmDeleteDialog(
                        title = stringResource(R.string.confirm_delete),
                        description = stringResource(R.string.confirm_delete_desc),
                        password = passwordInput,
                        onPasswordChange = { passwordInput = it },
                        confirmButtonText = stringResource(R.string.delete),
                        cancelButtonText = stringResource(R.string.cancel),
                        onConfirm = {
                            val email = userAuthenticationViewModel.email
                            val password = passwordInput
                            if (email.isNotBlank() && password.isNotBlank()) {
                                accountViewModel.deleteAccountWithPassword(email, password)
                            } else {
                                accountViewModel.updateError =
                                    context.getString(R.string.complete_all_fields)
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
}