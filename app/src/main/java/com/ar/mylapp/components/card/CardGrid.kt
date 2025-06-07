package com.ar.mylapp.components.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import ar.com.myldtos.cards.CardDTO
import coil3.compose.AsyncImage
import com.ar.mylapp.R
import com.ar.mylapp.navigation.Screens

@Composable
fun CardGrid(
    navController: NavController,
    cards: List<CardDTO>,
    isLoading: Boolean,
    onLoadMore: () -> Unit
) {

    val listState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 100.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        state = listState
    ) {
        items(cards) {
            CardGridImage(
                card = it,
                onClick = { navController.navigate(Screens.CardDetail.withArgs(it.id)) }
            )
        }

        if (isLoading) {
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

    val shouldLoadMore = remember {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            val totalItems = layoutInfo.totalItemsCount
            val lastVisible = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisible >= totalItems - 1
        }
    }

    LaunchedEffect(shouldLoadMore.value) {
        if (shouldLoadMore.value && !isLoading) {
            onLoadMore()
        }
    }
}

@Composable
fun CardGridImage(
    card: CardDTO,
    onClick: () -> Unit
) {
    AsyncImage(
        model = card.imageUrl,
        contentDescription = card.name,
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(id = R.drawable.placeholder),
        error = painterResource(id = R.drawable.error),
        modifier = Modifier
            .aspectRatio(512f / 734f)
            .clickable { onClick() }
    )
}
