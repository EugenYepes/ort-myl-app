package com.ar.mylapp.components.buttons

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.Gray

@Composable
fun WhatsAppButton2(
    modifier: Modifier = Modifier,
    phoneNumber: String,
    enabled: Boolean = true,
    backgroundColor: Color = GoldDark,
    borderColor: Color = Color.Transparent
) {
    val context = LocalContext.current

    IconButton(
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
        modifier = modifier
            .size(width = 60.dp, height = 60.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(if (enabled) backgroundColor else Gray)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(size = 50.dp)
            ),
    ) {
        Image(
            painter = painterResource(id = R.drawable.whatsapp_icon),
            colorFilter = ColorFilter.tint(if(enabled) GoldDark else Black),
            contentDescription = stringResource(R.string.wpp_desc),
            modifier = Modifier.size(32.dp)
        )
    }
}