package com.ar.mylapp.components.entryData

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import com.ar.mylapp.ui.theme.inputOneStyle
import com.ar.mylapp.ui.theme.labelStyle
import com.ar.mylapp.ui.theme.outlinedTextFieldOneStyle

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
fun InputOnePreview() {
    InputOne(
        label = "Input #1"
    )
}

@Composable
fun InputOne(
    label: String
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = true,
        label = {
            Text(
                label,
                style = labelStyle
            )
        },
        modifier = Modifier
            .width(326.dp)
            .height(60.dp),
        textStyle = inputOneStyle,
        shape = RoundedCornerShape(15.dp),
        colors = outlinedTextFieldOneStyle()
    )
}