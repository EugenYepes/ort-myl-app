package com.ar.mylapp.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ar.com.myldtos.cards.CardDTO
import coil3.compose.AsyncImage
import com.ar.mylapp.R
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldBeige

@Composable
fun CardGrid(
    navController: NavController,
    cards: List<CardDTO>,
    isLoading: Boolean = false,
    onLoadMore: () -> Unit = {},
    enablePagination: Boolean = true,
    showQuantityNumber: Boolean = false,
    getQuantityForCard: ((CardDTO) -> Int)? = null
) {
    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        items(cards) { card ->
            val quantity = if (showQuantityNumber && getQuantityForCard != null) {
                getQuantityForCard(card)
            } else 0

            CardGridImage(
                card = card,
                onClick = { navController.navigate(Screens.CardDetail.withArgs(card.id)) },
                showQuantityNumber = showQuantityNumber,
                quantity = quantity
            )
        }

        if (enablePagination && isLoading) {
            item(span = { GridItemSpan(maxLineSpan) }) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }

    if (enablePagination) {
        val shouldLoadMore = remember {
            derivedStateOf {
                val layoutInfo = listState.layoutInfo
                val totalItems = layoutInfo.totalItemsCount
                val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
                lastVisible >= totalItems - 1
            }
        }

        LaunchedEffect(shouldLoadMore.value) {
            if (shouldLoadMore.value && !isLoading) { onLoadMore() }
        }
    }
}

@Composable
fun CardGridImage(
    card: CardDTO,
    onClick: () -> Unit,
    quantity: Int,
    showQuantityNumber: Boolean
) {
    Box(
        modifier = Modifier
            .aspectRatio(512f / 734f)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = card.imageUrl,
            contentDescription = card.name,
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.error),
            modifier = Modifier.fillMaxSize()
        )

        if (showQuantityNumber && quantity > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
                    .size(24.dp)
                    .background(Black, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = quantity.toString(),
                    color = GoldBeige,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
