package com.ar.mylapp.components.textComponent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.Beige

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Text5Preview(){
    Text5(
        text = "Texto #4"
    )
}

@Composable
fun Text5(
    text: String,
    modifier: Modifier = Modifier
){
    Text(
        text = text,
        modifier = modifier,
        style = TextStyle(
            fontSize = 18.sp,
            lineHeight = 18.sp,
            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
            fontWeight = FontWeight(400),
            color = Beige,
            textAlign = TextAlign.Center,
        )
    )
}