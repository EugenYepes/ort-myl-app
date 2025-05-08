package com.ar.mylapp.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.ar.mylapp.R
import com.ar.mylapp.models.Card
import com.ar.mylapp.navigation.Screens

@Composable
fun CardGrid(
    navController: NavController,
    cards: List<Card>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(cards) {
            CardGridImage(
                card = it,
                onClick = { navController.navigate(Screens.CardDetail.withArgs(it.cardId)) }
            )
        }
    }
}

@Composable
fun CardGridImage(
    card: Card,
    onClick: () -> Unit
) {
    AsyncImage(
        model = card.imageUrl,
        contentDescription = card.cardName,
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(id = R.drawable.placeholder),
        error = painterResource(id = R.drawable.error),
        modifier = Modifier
            .aspectRatio(512f / 734f)
            .clickable { onClick() }
    )
}
