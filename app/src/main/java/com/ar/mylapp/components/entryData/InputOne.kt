package com.ar.mylapp.components.entryData
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldDark
import androidx.compose.material3.OutlinedTextFieldDefaults

@Preview
@Composable
fun InputOnePreview() {
    InputOne()
}

@Composable
fun InputOne() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = {
            Text(
                "Input #1",
                style = customTextStyle
            )
        },
        textStyle = customTextStyle,
        shape = RoundedCornerShape(15.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = GoldDark,    // Color cuando está enfocado (activo)
            unfocusedBorderColor = GoldDark  // Color cuando no está enfocado
        )
    )}

val customTextStyle = TextStyle(
    fontSize = 15.sp,
    lineHeight = 18.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400),
    color = GoldDark,
)