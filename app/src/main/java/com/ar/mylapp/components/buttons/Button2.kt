package com.ar.mylapp.components.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.GreenDark
import com.ar.mylapp.ui.theme.labelStyle

@Preview
@Composable
fun Button2Preview() {
    Button2(
        onClick = {},
        text = "Botón #2"
    )
}

@Composable
fun Button2(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .size(width = 220.dp, height = 50.dp)
            .border(
                width = 1.dp,
                color = GoldLight,
                shape = RoundedCornerShape(size = 20.dp),
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenDark,
            contentColor = GoldDark),
        shape = RoundedCornerShape(20.dp)
    )
    {
        Text(
            text = text,
            style = labelStyle
        )
    }
}