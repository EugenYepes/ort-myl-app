package com.ar.mylapp.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ar.com.myldtos.cards.CardDTO
import coil3.compose.AsyncImage

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun CardDetailImage(
    card: CardDTO,
    alignment: Alignment,
    onClick: () -> Unit
) {
    BoxWithConstraints(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = alignment
    ) {
        val screenWidth = maxWidth
        val imageWidth = screenWidth * 0.8f
        val imageHeight = imageWidth * 1.43f

        AsyncImage(
            model = card.imageUrl,
            contentDescription = card.name,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = com.ar.mylapp.R.drawable.placeholder),
            error = painterResource(id = com.ar.mylapp.R.drawable.error),
            modifier = Modifier
                .width(imageWidth)
                .height(imageHeight)
                .clip(shape = RoundedCornerShape(10.dp))
                .background(color = Color.Transparent, shape = RoundedCornerShape(15.dp))
                .clickable { onClick() }
        )
    }
}