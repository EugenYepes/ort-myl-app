package com.ar.mylapp.components.deck

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.components.tittle.Tittle2
import com.ar.mylapp.components.tittle.Tittle3
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.TransparentDark

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun DeckNameCardPreview() {
    DeckNameCard(
        title2 = "Ejemplo Nombre de Carta",
        title3 = "n/50" // Modificar según Issue 31
    )
}

@Composable
fun DeckNameCard(
    title2: String,
    title3: String
) {
    Card(
        modifier = Modifier
            .border(width = 1.dp, color = GoldDark)
            .width(366.dp)
            .height(67.dp)
            .background(color = TransparentDark),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Tittle2(
                tittle = title2,
                modifier = Modifier.widthIn(max = 250.dp)
            )
            Tittle3(
                tittle = title3
            )
        }
    }
}