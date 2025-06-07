package com.ar.mylapp.components.entryData

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDropdownMenu(
    modifier: Modifier,
    label: String,
    options: List<String>,
    selectedOptions: List<String>,
    onOptionToggled: (String, Boolean) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    //Variables para controlar color del borde de Box
    var isFocused by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    val focusModifier = Modifier.onFocusChanged {
        isFocused = it.isFocused
    }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        Box(
            modifier = modifier
                .width(300.dp)
                .border(
                    width = 2.dp,
                    color = if (isFocused) GoldDark else Color.White,
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .padding(2.dp)
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
                    .width(300.dp)
                    .then(focusModifier)
                    .focusRequester(focusRequester)
                    /*.border(
                        width = 2.dp,
                        //color = GoldDark,
                        //color = if (isFocused) GoldDark else Color.White,
                        shape = RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )*/
                    .clip(
                        RoundedCornerShape(
                            topStart = 8.dp,
                            topEnd = 8.dp,
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp
                        )
                    ),
                colors = exposedDropdownStyle(),
                textStyle = textDropdownMenuStyle,
                readOnly = true,
                value = selectedOptions.joinToString(", "),
                onValueChange = {}, // Se deja vacío porque es readOnly
                label = {
                    Text(
                        label,
                        style = labelDropdownMenuStyle
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.trailing_arrow),
                        "Flecha hacia abajo",
                        tint = White
                    )
                },
            )
        }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = GoldDark,
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 8.dp
                    )
                )
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 8.dp
                    )
                )
                .background(Black),
            containerColor = Color.Transparent,
        ) {
            options
                .forEach { option ->
                    val isSelected = selectedOptions.contains(option)
                    DropdownMenuItem(
                        onClick = { },
                        text = {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = isSelected,
                                    //Función llamada cuando se presiona el checkbox
                                    onCheckedChange = { checked ->
                                        onOptionToggled(option, checked)
                                    },
                                    colors = CheckboxColors(
                                        checkedBorderColor = White,
                                        uncheckedBorderColor = White,
                                        checkedCheckmarkColor = GoldDark,
                                        uncheckedCheckmarkColor = White,
                                        checkedBoxColor = Color.Transparent,
                                        uncheckedBoxColor = Color.Transparent,
                                        disabledBorderColor = White,
                                        disabledIndeterminateBorderColor = White,
                                        disabledUncheckedBorderColor = White,
                                        disabledCheckedBoxColor = Color.Transparent,
                                        disabledUncheckedBoxColor = Color.Transparent,
                                        disabledIndeterminateBoxColor = Color.Transparent
                                    )
                                )
                                Text(
                                    text = option,
                                    style = textDropdownMenuStyle,
                                    color = if (isSelected) White else Gray
                                )
                            }
                        },
                        modifier = Modifier.background(Black),
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
        }
    }
}

@Preview
@Composable
fun MyDropdownMenuPreview() {
    val typeOptions = listOf("Aliado", "Talismán", "Arma", "Tótem", "Oro", "Monumento")
    val selectedTypes = remember { mutableStateListOf<String>() }

    MyDropdownMenu(
        label = "Tipo",
        options = typeOptions,
        selectedOptions = selectedTypes,
        onOptionToggled = { option, isChecked ->
            if (isChecked) selectedTypes.add(option)
            else selectedTypes.remove(option)
        },
        modifier = Modifier,
    )
}
