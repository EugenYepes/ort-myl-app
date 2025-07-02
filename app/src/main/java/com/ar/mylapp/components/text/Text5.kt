package com.ar.mylapp.components.text

import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldBeige

@Composable
fun Text5(
    text: String,
    modifier: Modifier = Modifier,
    color: Color = GoldBeige,
    fontSize: TextUnit = 18.sp,
    maxLines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
    textAlign: TextAlign = TextAlign.Center
) {
    Text(
        text = text,
        modifier = modifier,
        maxLines = maxLines,
        overflow = overflow,
        style = TextStyle(
            fontSize = fontSize,
            lineHeight = 18.sp,
            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
            fontWeight = FontWeight(400),
            color = color,
            textAlign = textAlign,
        )
    )
}