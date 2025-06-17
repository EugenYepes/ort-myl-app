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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ar.mylapp.components.title.Title2
import com.ar.mylapp.components.title.Title3
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.BlackLight

@Composable
fun DeckNameCard(
    title2: String,
    title3: String,
    modifier: Modifier = Modifier,
    titleSize: TextUnit = 32.sp
) {
    Card(
        modifier = modifier
            .border(width = 1.dp, color = GoldDark)
            .width(366.dp)
            .height(67.dp)
            .background(color = BlackLight),
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
            Title2(
                title = title2,
                modifier = Modifier.widthIn(max = 250.dp),
                titleSize = titleSize
            )
            Title3(
                title = "$title3/50",
                titleSize = titleSize
            )
        }
    }
}