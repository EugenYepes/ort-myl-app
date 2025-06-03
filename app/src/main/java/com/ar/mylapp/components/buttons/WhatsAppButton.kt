package com.ar.mylapp.components.buttons

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.net.toUri

@Composable
fun WhatsAppButton(
    phoneNumber: String
) {
    val context = LocalContext.current

    Button(
        onClick = {
            val uri = "https://wa.me/$phoneNumber".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)

            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
            }
        }
    ) {
        Text("Contactar por WhatsApp")
    }
}