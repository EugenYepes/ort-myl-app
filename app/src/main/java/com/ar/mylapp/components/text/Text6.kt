package com.ar.mylapp.components.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldDark

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Text6Preview(){
    Text6(
        text = "Texto #6",
        modifier = Modifier
    )
}

@Composable
fun Text6(
    text: String,
    modifier: Modifier = Modifier,
    buttonTextSize: TextUnit = 20.sp
) {
    Text(
        text = text,
        modifier = Modifier,
        style = TextStyle(
            fontSize = buttonTextSize,
            lineHeight = buttonTextSize * 1.25,
            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
            fontWeight = FontWeight(400),
            color = GoldDark,
            textAlign = TextAlign.Center
        )
    )
}