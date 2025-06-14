package com.ar.mylapp.components.buttons

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.Gray
import com.ar.mylapp.ui.theme.GreenDark
import com.ar.mylapp.ui.theme.labelStyle

@Composable
fun WhatsAppButton(
    phoneNumber: String,
    enabled: Boolean = true
) {
    val context = LocalContext.current

    Button(
        onClick = {
            val uri = "https://wa.me/$phoneNumber".toUri()
            val intent = Intent(Intent.ACTION_VIEW, uri)

            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(
                    context,
                    context.getString(
                        R.string.whatsapp_not_installed
                    ),
                    Toast.LENGTH_SHORT
                ).show()
            }
        },
        enabled = enabled,
        modifier = Modifier
            .border(
                width = 1.dp,
                color = if (enabled) GoldLight else Gray,
                shape = RoundedCornerShape(size = 20.dp),
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenDark,
            contentColor = GreenDark
        ),
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            text = stringResource(R.string.button_wpp),
            style = labelStyle,
            color = if (enabled) GoldDark else Gray
        )
    }
}