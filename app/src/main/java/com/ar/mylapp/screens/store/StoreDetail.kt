package com.ar.mylapp.screens.store

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.viewmodel.TopBarViewModel
import androidx.core.net.toUri

@Composable
fun StoreDetailScreen(
    navController: NavController,
    topBarViewModel: TopBarViewModel
){
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("TIENDA")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Store Detail Screen",
                fontSize = 30.sp,
                color = GoldDark
            )
            WhatsAppButton("5491169077535")
        }
    }
}

@Composable
fun WhatsAppButton(phoneNumber: String) {
    val context = LocalContext.current

    Button(onClick = {
        val uri = "https://wa.me/$phoneNumber".toUri()
        val intent = Intent(Intent.ACTION_VIEW, uri)

        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
        }
    }) {
        Text("Contactar por WhatsApp")
    }
}
