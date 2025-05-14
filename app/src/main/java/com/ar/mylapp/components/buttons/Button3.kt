package com.ar.mylapp.components.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.components.text.Text6
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.TransparentGreen

@Preview
@Composable
fun Button3Preview() {
    Button3(
        onClick = {},
        text = "Botón #3"
    )
}

@Composable
fun Button3(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(width = 160.dp, height = 50.dp)
            .border(
                width = 2.dp,
                color = GoldLight,
                shape = RoundedCornerShape(size = 20.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = TransparentGreen,
            contentColor = GoldDark),
        shape = RoundedCornerShape(20.dp)
    )
    {
        Text6(
            text = text
        )
    }
}