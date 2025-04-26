package com.ar.mylapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ar.mylapp.R

@Composable
fun MYLAPPTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography,
        content = content,
        
    )
}