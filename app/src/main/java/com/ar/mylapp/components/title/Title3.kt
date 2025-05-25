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
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldBeige

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Title3Preview(){
    Title3(
        title = "Title #3"
    )
}

@Composable
fun Title3(
    title: String,
    modifier: Modifier = Modifier
){
    Text(
        text = title,
        modifier = modifier,
        style = TextStyle(
            fontSize = 32.sp,
            lineHeight = 18.sp,
            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
            fontWeight = FontWeight(400),
            color = GoldBeige,
            textAlign = TextAlign.Center,
        ),
        maxLines = 1,
        softWrap = true,
        overflow = TextOverflow.Ellipsis
    )
}