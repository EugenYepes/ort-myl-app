package com.ar.mylapp.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark

@Preview
@Composable
fun Button8Preview() {
    Button8(
        onClick = {},
        text = "Volver a la carta"
    )
}

@Composable
fun Button8(
    onClick: () -> Unit,
    text: String
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .wrapContentWidth()
            .height(50.dp)
            .padding(horizontal = 14.dp)
            /*.border(
                width = 1.dp,
                color = GoldDark,
                shape = RoundedCornerShape(size = 6.dp)
            )*/
            .background(color = GoldDark, shape = RoundedCornerShape(size = 6.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = GoldDark,
            contentColor = Black
        ),
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontWeight = FontWeight(400),
                color = Black,
                textAlign = TextAlign.Center,
            ),
            maxLines = 1
        )
    }
}