package com.ar.mylapp.components.buttons

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun WhatsAppButton(
    phoneNumber: String
) {
    val context = LocalContext.current

    Button2(
        onClick = {
            val uri = "https://wa.me/$phoneNumber".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)

            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(context, "WhatsApp no está instalado", Toast.LENGTH_SHORT).show()
            }
        },
        text = "Contactar por WhatsApp",
        modifier = Modifier.width(280.dp)
    )
}

