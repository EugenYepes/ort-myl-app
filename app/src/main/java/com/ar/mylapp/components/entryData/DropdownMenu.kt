package com.ar.mylapp.components.entryData

import androidx.compose.foundation.background
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.Gray
import com.ar.mylapp.ui.theme.White

@Preview
@Composable
fun DropdownMenuPreview() {
    val typeOptions = listOf("Aliado", "Talismán", "Arma", "Tótem", "Oro", "Monumento")
    var selectedType by remember { mutableStateOf(typeOptions[0]) }

    DropdownMenu(
        label = "Tipo",
        options = typeOptions,
        selectedOption = selectedType,
        onOptionSelected = { selectedType = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it }
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor(),
            textStyle = customDropdownTextStyle,
            readOnly = true,
            value = selectedOption,
            onValueChange = {}, // Se deja vacío porque es readOnly
            label = { Text(
                label,
                style = customDropdownLabelStyle) },
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.trailing_arrow),
                    "Flecha hacia abajo",
                    tint = White
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedTextColor = White,
                unfocusedTextColor = White,
                focusedContainerColor = Black,
                unfocusedContainerColor = Black,
                focusedLabelColor = GoldDark,
                unfocusedLabelColor = GoldDark,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Black)
        ) {
            options.forEach { option ->
                val isSelected = option == selectedOption
                DropdownMenuItem(
                    text = { Text(
                        option,
                        style = customDropdownTextStyle,
                        color = if (isSelected) White else Gray) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

val customDropdownTextStyle = TextStyle(
    fontSize = 18.sp,
    lineHeight = 16.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400),
    color = White
)

val customDropdownLabelStyle = TextStyle(
    fontSize = 12.sp,
    lineHeight = 10.sp,
    fontFamily = FontFamily(Font(R.font.patua_one_regular)),
    fontWeight = FontWeight(400),
    color = GoldDark
)
