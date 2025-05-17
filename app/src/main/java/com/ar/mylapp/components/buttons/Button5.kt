package com.ar.mylapp.components.buttons

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.ar.mylapp.components.text.Text1

@Preview
@Composable
fun Button5Preview() {
    Button5(
        onClick = {},
        text = "Botón #5"
    )
}

@Composable
fun Button5(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        )
    )
    {
        Text1(
            text = text,
            modifier = Modifier
        )
    }
}