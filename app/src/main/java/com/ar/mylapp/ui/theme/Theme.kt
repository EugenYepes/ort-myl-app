package com.ar.mylapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    background = Color(0xFF000000),
    surface = Color(0xFF555555),
    primary = Color(0xFFD1AC4B),
    secondary = Color(0x8F112B0A)
)

@Composable
fun MYLAPPTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}