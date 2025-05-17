package com.ar.mylapp.components.entryData

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.Gray
import com.ar.mylapp.ui.theme.searchBarStyle

@Preview(showBackground = true, backgroundColor = 0x00000000)
@Composable
fun MySearchBarPreview() {
    MySearchBar(
        placeholder = "Buscar..."
    )
}

@Composable
fun MySearchBar(
    placeholder: String
) {
    var text by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(Black, shape = RoundedCornerShape(50.dp))
            .clip(RoundedCornerShape(50.dp)) // Asegura que el contenido quede dentro
            .border(
                width = 4.dp,
                color = GoldDark,
                shape = RoundedCornerShape(50.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.search),
                contentDescription = "search",
                tint = Gray,
                modifier = Modifier
                    .padding(start = 10.dp)
                    .size(24.dp)
            )

            Spacer(modifier = Modifier.width(8.dp))

            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                singleLine = true,
                placeholder = {
                    Text(
                        placeholder,
                        style = searchBarStyle
                    )
                },
                textStyle = searchBarStyle,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Black,
                    unfocusedContainerColor = Black,
                    disabledContainerColor = Black,
                    focusedPlaceholderColor = GoldDark,
                    unfocusedPlaceholderColor = Gray,
                    cursorColor = GoldDark,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
        }
    }
}