package com.ar.mylapp.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import com.ar.mylapp.navigation.Screens
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

@Composable
fun LoginScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Este bloque comentado hace la authenticacion
        // con Google y Firebase

      /*  val context = LocalContext.current
        // Autenticar con Google
        // El launcher inicia la actividad de Google y espera el resultado
        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult()
        ) { result ->
            // Intentos de autenticar al usuario
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                // Intentamos autenticar al usuario
                val account = task.getResult(ApiException::class.java)
                // Si la autenticación es exitosa, obtenemos el token
                // y lo pasamos a Firebase
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential)
                    .addOnSuccessListener {
                        // Login OK: accedés al usuario
                        FirebaseAuth.getInstance().currentUser?.getIdToken(true)
                            ?.addOnSuccessListener { result ->
                                val firebaseToken = result.token
                                // Guardamos el token en el ViewModel
                                userAuthenticationViewModel.token = firebaseToken
                                userAuthenticationViewModel.email = FirebaseAuth.getInstance().currentUser?.email ?: "Usuario"
                            }
                            ?.addOnFailureListener {
                                userAuthenticationViewModel.error = "Error obteniendo token: ${it.message}"
                            }
                    }
                    .addOnFailureListener {
                        userAuthenticationViewModel.error = it.message
                    }
            } catch (e: ApiException) {
                userAuthenticationViewModel.error = "Error al autenticar con Google: ${e.message}"
            }
        }
        */


        TextField(
            value = userAuthenticationViewModel.email,
            onValueChange = { userAuthenticationViewModel.email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = userAuthenticationViewModel.password,
            onValueChange = { userAuthenticationViewModel.password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { userAuthenticationViewModel.onLoginClicked() }) {
            Text("Login")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { userAuthenticationViewModel.onRegisterClicked() }) {
            Text("Register")
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Comento boton de acceso con google
      /*  Button(onClick = {
            val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("1055501727698-j0umm7kllbvpgoufnbvrnsfu1ap2mm5b.apps.googleusercontent.com")
                .requestEmail()
                .build()

            val googleSignInClient = GoogleSignIn.getClient(context, gso)
            launcher.launch(googleSignInClient.signInIntent)
        }) {
            Text("Iniciar sesión con Google")
        }
       */

        userAuthenticationViewModel.error?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }

        userAuthenticationViewModel.token?.let {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Token recibido ✅", color = MaterialTheme.colorScheme.primary)
        }

        // Se guarda la instancia del usuario de firebase
        // para verificar si el usuario ya está logueado
        // y derivar a pantalla correcta
        val firebaseUser = FirebaseAuth.getInstance().currentUser

        // Si el usuario no es nulo, significa que ya está logueado
        LaunchedEffect(firebaseUser) {
            if (firebaseUser != null) {
                navController.navigate(Screens.Home.screen) {
                    popUpTo(Screens.Login.screen) { inclusive = true }
                }
            }
        }
    }
}
