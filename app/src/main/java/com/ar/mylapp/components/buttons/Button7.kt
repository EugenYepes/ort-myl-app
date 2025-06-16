package com.ar.mylapp.components.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark

@Preview
@Composable
fun Button7Preview() {
    Button7(
        onClick = {},
        text = "Volver a la carta"
    )
}

@Composable
fun Button7(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .wrapContentWidth()
            .height(50.dp)
            .padding(horizontal = 6.dp)
            .border(
                width = 1.dp,
                color = GoldDark,
                shape = RoundedCornerShape(size = 6.dp)
            )
            .background(color = Black, shape = RoundedCornerShape(size = 6.dp)),
        colors = ButtonDefaults.buttonColors(
            containerColor = Black,
            contentColor = GoldDark
        ),
    ) {
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontWeight = FontWeight(400),
                color = GoldDark,
                textAlign = TextAlign.Center,
            ),
            maxLines = 1,
            softWrap = true,
            overflow = TextOverflow.Ellipsis
        )
    }
}