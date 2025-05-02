package com.ar.mylapp.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.mock.Card
import com.ar.mylapp.navigation.Screens

@Composable
fun CardGrid(
    navController: NavController,
    cards: List<Card>
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp), // Tamaño mínimo de cada celda
        contentPadding = PaddingValues(8.dp), // Espacio alrededor de la cuadrícula
        verticalArrangement = Arrangement.spacedBy(8.dp), // Espacio vertical entre las filas
        horizontalArrangement = Arrangement.spacedBy(8.dp) // Espacio horizontal entre las columnas
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
    Image(
        painter = painterResource(id = card.imageResId),
        contentDescription = card.cardName,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .width(105.dp)
            .height(151.dp)
            .clickable { onClick() }
    )
}
