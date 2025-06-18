package com.ar.mylapp.components.title

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldDark

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Title5Preview(){
    Title5(
        title = "Title #5"
    )
}

@Composable
fun Title5(
    title: String
){
    Text(
        text = title,
        modifier = Modifier
            .width(300.dp)
            .height(24.dp),
        style = TextStyle(
            fontSize = 20.sp,
            lineHeight = 18.sp,
            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
            fontWeight = FontWeight(400),
            color = GoldDark,
            textAlign = TextAlign.Left,
        ),
        maxLines = 1,
        softWrap = true,
        overflow = TextOverflow.Ellipsis
    )
}