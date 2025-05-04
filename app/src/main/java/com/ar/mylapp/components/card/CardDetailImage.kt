package com.ar.mylapp.components.card

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.ar.mylapp.models.Card

@Composable
fun CardDetailImage(card: Card) {
    AsyncImage(
        model = card.imageUrl,
        contentDescription = card.cardName,
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(id = com.ar.mylapp.R.drawable.placeholder),
        error = painterResource(id = com.ar.mylapp.R.drawable.error),
        modifier = Modifier
            .width(382.dp)
            .height(548.dp)
    )
}

