package com.ar.mylapp.components.tittleComponent

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
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.R

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Tittle2Preview(){
    Tittle2(
        tittle = "Tittle #2"
    )
}

@Composable
fun Tittle2(
    tittle: String,
    modifier: Modifier = Modifier
){
    Text(
        text = tittle,
        modifier = modifier,
        style = TextStyle(
            fontSize = 32.sp,
            lineHeight = 18.sp,
            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
            fontWeight = FontWeight(400),
            color = GoldDark,
            textAlign = TextAlign.Center,
        )
    )
}