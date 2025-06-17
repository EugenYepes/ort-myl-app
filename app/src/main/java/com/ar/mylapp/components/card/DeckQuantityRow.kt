package com.ar.mylapp.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ar.mylapp.components.number_picker.NumberPicker
import com.ar.mylapp.components.text.Text5

@Composable
fun DeckQuantityRow(
    deckName: String,
    quantity: Int,
    onQuantityChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text5(
            text = deckName,
            modifier = Modifier.weight(1f)
        )
        NumberPicker(
            modifier = Modifier.width(120.dp),
            number = quantity,
            onValueChange = onQuantityChange
        )
    }
}