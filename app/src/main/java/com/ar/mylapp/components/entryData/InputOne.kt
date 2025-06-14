package com.ar.mylapp.components.entryData

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.inputOneStyle
import com.ar.mylapp.ui.theme.labelStyle
import com.ar.mylapp.ui.theme.outlinedTextFieldOneStyle

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
fun InputOnePreview() {
    var text by remember { mutableStateOf("Texto de prueba") }
    InputOne(
        label = "Input #1",
        value = text,
        onValueChange = { text = it }
    )
}

@Composable
fun InputOne(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    isPassword: Boolean = false,
    enabled: Boolean = true
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        singleLine = true,
        label = {
            Text(
                label,
                style = labelStyle
            )
        },
        modifier = Modifier
            .width(326.dp)
            .height(62.dp),
        textStyle = inputOneStyle,
        shape = RoundedCornerShape(15.dp),
        colors = outlinedTextFieldOneStyle(),
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = {
            if (isPassword) {
                val icon = if (passwordVisible) R.drawable.visibility_icon else R.drawable.visibility_off_icon
                val description = if (passwordVisible) stringResource(R.string.hide_password) else stringResource(R.string.show_password)

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = description,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    )
}

