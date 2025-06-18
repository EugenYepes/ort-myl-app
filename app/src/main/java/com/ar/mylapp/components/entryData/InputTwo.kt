package com.ar.mylapp.components.entryData

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ar.mylapp.ui.theme.inputTwoThreeStyle
import com.ar.mylapp.ui.theme.outlinedTextFieldTwoThreeStyle

@Composable
fun InputTwo(
    label: String,
    initialValue: String = "",
    onValueChange: (String) -> Unit
) {
    Column {
        Text(
            text = label,
            style = inputTwoThreeStyle,
            modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
        )
        OutlinedTextField(
            value = initialValue,
            onValueChange = onValueChange,
            singleLine = true,
            modifier = Modifier
                .width(326.dp)
                .height(60.dp),
            textStyle = inputTwoThreeStyle,
            colors = outlinedTextFieldTwoThreeStyle()
        )
    }
}