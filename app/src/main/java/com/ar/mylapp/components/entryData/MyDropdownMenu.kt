package com.ar.mylapp.components.entryData

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.Gray
import com.ar.mylapp.ui.theme.White
import com.ar.mylapp.ui.theme.exposedDropdownStyle
import com.ar.mylapp.ui.theme.labelDropdownMenuStyle
import com.ar.mylapp.ui.theme.textDropdownMenuStyle

@Preview
@Composable
fun MyDropdownMenuPreview() {
    val typeOptions = listOf("Aliado", "Talismán", "Arma", "Tótem", "Oro", "Monumento")
    var selectedType by remember { mutableStateOf(typeOptions[0]) }

    MyDropdownMenu(
        label = "Tipo",
        options = typeOptions,
        selectedOption = selectedType,
        onOptionSelected = { selectedType = it }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu(
    label: String,
    options: List<String>,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            /* Sobre menuAnchor():
            Este modifier marca un elemento como el "ancla" donde el menú desplegable debe aparecer (TextField).
            Sin ese modificador, Compose no sabrá dónde colocar el menú cuando esté expandido,
            por eso si es eliminado, no se muestra la lista de items

            Para que no tire advertencia "deprecated" se completaría con estos parámetros:
            MenuAnchorType.Dropdown, enabled = true
            Pero solo aplica a versiones de Material3 1.2.0-alpha06 o superior
             */
            modifier = Modifier
                .menuAnchor()
                .border(
                    width = 4.dp,
                    color = GoldDark,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp)),
            textStyle = textDropdownMenuStyle,
            readOnly = true,
            value = selectedOption,
            onValueChange = {}, // Se deja vacío porque es readOnly
            label = { Text(
                label,
                style = labelDropdownMenuStyle) },
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.trailing_arrow),
                    "Flecha hacia abajo",
                    tint = White
                )
            },
            colors = exposedDropdownStyle()
            )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .border(
                    width = 4.dp,
                    color = GoldDark,
                    shape = RoundedCornerShape(20.dp)
                )
                .clip(RoundedCornerShape(20.dp))
                .background(Black),
            containerColor = Color.Transparent
        ) {
            options
                .filter { it != selectedOption } // Que no repita la opción ya seleccionada
                .forEach { option ->
                val isSelected = option == selectedOption
                DropdownMenuItem(
                    text = { Text(
                        option,
                        style = textDropdownMenuStyle,
                        color = if (isSelected) White else Gray) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    modifier = Modifier.background(Black),
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}
