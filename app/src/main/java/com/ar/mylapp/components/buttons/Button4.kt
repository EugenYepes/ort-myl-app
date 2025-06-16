package com.ar.mylapp.components.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.components.text.Text7
import com.ar.mylapp.ui.theme.DarkRed
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight

@Preview
@Composable
fun Button4Preview() {
    Button4(
        onClick = {},
        text = "Botón #4"
    )
}

@Composable
fun Button4(
    onClick: () -> Unit,
    text: String,
    buttonTextSize: TextUnit = 20.sp,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .height(50.dp)
            .border(
                width = 1.dp,
                color = GoldLight,
                shape = RoundedCornerShape(size = 20.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = DarkRed,
            contentColor = GoldDark
        ),
        shape = RoundedCornerShape(20.dp),
        contentPadding = ButtonDefaults.ContentPadding
    ) {
        Text7(
            text = text,
            modifier = Modifier,
            buttonTextSize = buttonTextSize
        )
    }
}