package com.ar.mylapp.components.entryData

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.ui.theme.inputTwoThreeStyle
import com.ar.mylapp.ui.theme.outlinedTextFieldTwoThreeStyle

@Preview
@Composable
fun InputThreePreview() {
    InputThree(
        label = "Input #3"
    )
}

@Composable
fun InputThree(
    label: String
) {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = {
            Text(
                label,
                style = inputTwoThreeStyle
            )
        },
        modifier = Modifier
            .width(326.dp)
            .height(192.dp),
        textStyle = inputTwoThreeStyle,
        colors = outlinedTextFieldTwoThreeStyle()
    )
}