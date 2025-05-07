package com.ar.mylapp.components.entryData

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.Gray
import com.ar.mylapp.ui.theme.DarkGray

@Preview
@Composable
fun InputTwo() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = {
            Text(
                "Input #2",
                style = customInputTwoTextStyle,
                modifier = Modifier.background(color = Color.Transparent)
            )
        },
        modifier = Modifier
            .width(326.dp)
            .height(60.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Gray,
            unfocusedContainerColor = DarkGray,
            disabledContainerColor = DarkGray,
            focusedBorderColor = GoldDark,
            unfocusedBorderColor = Black,
            focusedLabelColor = GoldDark,
            unfocusedLabelColor = Gray,
            cursorColor = GoldDark
        )
    )
}

val customInputTwoTextStyle = TextStyle(
    fontSize = 15.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400)
)