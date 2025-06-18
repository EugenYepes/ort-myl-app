package com.ar.mylapp.components.title

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.R

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Title2Preview(){
    Title2(
        title = "Title #2"
    )
}

@Composable
fun Title2(
    title: String,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = 32.sp
){
    Text(
        text = title,
        modifier = modifier,
        style = TextStyle(
            fontSize = titleSize,
            lineHeight = titleSize * 1.25,
            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
            fontWeight = FontWeight(400),
            color = GoldDark,
            textAlign = TextAlign.Center,
        ),
        softWrap = true,
        overflow = TextOverflow.Ellipsis
    )
}