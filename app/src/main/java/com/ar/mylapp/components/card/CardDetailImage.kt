package com.ar.mylapp.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ar.mylapp.mock.Card

@Composable
fun CardDetailImage(card: Card) {
    Image(
        painter = painterResource(card.imageResId),
        contentDescription = card.cardName,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .width(382.dp)
            .height(548.dp)
    )
}

