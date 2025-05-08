package com.ar.mylapp.components.text

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.TransparentDark

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Text4Preview(){
    Text4(
        text = "Texto #4"
    )
}

@Composable
fun Text4(
    text: String,
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier
            .border(width = 2.dp, color = GoldDark)
            .background(color = TransparentDark)
            .width(366.dp)
            .height(48.dp)
            .padding(horizontal = 8.dp, vertical = 14.dp)
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 15.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontWeight = FontWeight.Normal,
                color = GoldDark,
            ),
            modifier = Modifier
                .width(355.dp)
                .height(18.dp)
        )
    }
}