package com.ar.mylapp.components.text

import androidx.compose.foundation.layout.width
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
import com.ar.mylapp.ui.theme.GoldBeige

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Text9Preview(){
    Text9(
        text = "Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim velit mollit. Exercitation veniam consequat sunt nostrud ame."
    )
}

@Composable
fun Text9(
    text: String
){
    Text(
        text = text,
        modifier = Modifier
            .width(354.dp),
        style = TextStyle(
            fontSize = 16.sp,
            lineHeight = 28.sp,
            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
            fontWeight = FontWeight(400),
            color = GoldBeige,
            textAlign = TextAlign.Justify,
        ),
        //maxLines = 1,
        softWrap = true,
        //overflow = TextOverflow.Ellipsis
    )
}