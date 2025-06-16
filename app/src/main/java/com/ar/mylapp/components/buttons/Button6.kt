package com.ar.mylapp.components.buttons

import android.annotation.SuppressLint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.Gray
import com.ar.mylapp.ui.theme.GreenDark
import com.ar.mylapp.ui.theme.White

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun Button6(
    onClick: () -> Unit,
    text: String,
    icon: Painter,
    enabled: Boolean = true
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val textStartPadding = (screenWidth * 0.05).dp

    val iconSize = when {
        screenWidth < 360 -> 16.dp
        screenWidth < 420 -> 18.dp
        else -> 20.dp
    }

    val fontSize = when {
        screenWidth < 360 -> 12.sp
        screenWidth < 420 -> 14.sp
        else -> 16.sp
    }

    val buttonWidth = (screenWidth.dp - 24.dp * 2 - 40.dp) / 2

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .width(buttonWidth)
            .height(56.dp)
            .border(
                width = 1.dp,
                color = if (enabled) GoldLight else Gray,
                shape = RoundedCornerShape(size = 20.dp)
            ),
        contentPadding = PaddingValues(horizontal = 12.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenDark,
            contentColor = White,
            disabledContainerColor = GreenDark.copy(alpha = 0.5f),
            disabledContentColor = White.copy(alpha = 0.5f)
        ),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = icon,
                colorFilter = if (!enabled) ColorFilter.tint(Gray) else null,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .height(iconSize)
            )

            Text(
                text = text,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontSize = fontSize,
                maxLines = 1,
                softWrap = false,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = textStartPadding)
            )
        }
    }
}