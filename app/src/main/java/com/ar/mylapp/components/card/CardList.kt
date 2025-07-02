package com.ar.mylapp.components.card

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.R
import com.ar.mylapp.components.text.Text3
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.White
import com.ar.mylapp.utils.capitalizeTitle

private val NAME_WIDTH = 150.dp
private val FORCE_WIDTH = 60.dp
private val COST_WIDTH = 60.dp
private val RACE_WIDTH = 150.dp
private val RARITY_WIDTH = 150.dp
private val TYPE_WIDTH = 150.dp

@Composable
fun CardList(
    navController: NavController,
    cards: List<CardDTO>,
    isLoading: Boolean = false,
    onLoadMore: () -> Unit = {},
    enablePagination: Boolean = true
) {
    val listState = rememberLazyListState()
    val horizontalScrollState = rememberScrollState()

    Column(modifier = Modifier.fillMaxSize()) {
        CardListHeader(horizontalScrollState)

        LazyColumn(state = listState) {
            items(cards) { card ->
                CardListRow(
                    card = card,
                    onClick = { navController.navigate(Screens.CardDetail.withArgs(card.id)) },
                    scrollState = horizontalScrollState
                )
            }

            if (enablePagination && isLoading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
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
                if (shouldLoadMore.value && !isLoading) {
                    onLoadMore()
                }
            }
        }
    }
}

@Composable
fun CardListHeader(scrollState: ScrollState, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .clipToBounds()
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = modifier
                .horizontalScroll(scrollState)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text3(text = stringResource(R.string.cards_list_name), modifier = Modifier.width(NAME_WIDTH))
            Text3(text = stringResource(R.string.card_damage), modifier = Modifier.width(FORCE_WIDTH))
            Text3(text = stringResource(R.string.card_cost), modifier = Modifier.width(COST_WIDTH))
            Text3(text = stringResource(R.string.card_race), modifier = Modifier.width(RACE_WIDTH))
            Text3(text = stringResource(R.string.card_rarity), modifier = Modifier.width(RARITY_WIDTH))
            Text3(text = stringResource(R.string.card_type), modifier = Modifier.width(TYPE_WIDTH))
        }
    }
}

@Composable
fun CardListRow(
    card: CardDTO,
    onClick: () -> Unit,
    scrollState: ScrollState,
    modifier: Modifier = Modifier
) {
    val name = capitalizeTitle(card.name)
    val force = card.damage ?: stringResource(R.string.card_no_info)
    val cost = card.cost ?: stringResource(R.string.card_no_info)
    val race = card.race.name ?: stringResource(R.string.card_no_info)
    val rarity = card.rarity.name ?: stringResource(R.string.card_no_info)
    val type = card.type?.name ?: stringResource(R.string.card_type_unknown)

    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Box(modifier = Modifier.clipToBounds().padding(horizontal = 16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.horizontalScroll(scrollState)
            ) {
                Text5(
                    text = name,
                    color = White,
                    fontSize = 14.sp,
                    modifier = Modifier.width(NAME_WIDTH),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Start
                )
                Text5(
                    text = force.toString(),
                    modifier = Modifier.width(FORCE_WIDTH),
                    fontSize = 14.sp,
                    color = White,
                )
                Text5(
                    text = cost.toString(),
                    modifier = Modifier.width(COST_WIDTH),
                    fontSize = 14.sp,
                    color = White,
                )
                Text5(
                    text = race.toString(),
                    color = White,
                    fontSize = 14.sp,
                    modifier = Modifier.width(RACE_WIDTH),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text5(
                    text = rarity.toString(),
                    color = White,
                    fontSize = 14.sp,
                    modifier = Modifier.width(RARITY_WIDTH),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text5(
                    text = type,
                    color = White,
                    fontSize = 14.sp,
                    modifier = Modifier.width(TYPE_WIDTH),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
        HorizontalDivider(Modifier.padding(horizontal = 16.dp), 2.dp, GoldDark)
    }
}