package com.ar.mylapp.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCarousel(
    navController: NavController
) {

    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        val contentDescription: String
    )

    val items = remember {
        listOf(
            CarouselItem(13696, R.drawable.cardeolo, "cardeolo"),
            CarouselItem(1665, R.drawable.cardhelios, "cardhelios"),
            CarouselItem(14691, R.drawable.cardgaia, "cardgaia"),
            CarouselItem(14691, R.drawable.cardgaia, "cardgaia"),
            CarouselItem(14691, R.drawable.cardgaia, "cardgaia"),
            CarouselItem(14691, R.drawable.cardgaia, "cardgaia")
        )
    }

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { items.size }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) { page ->
            val item = items[page]

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = item.imageResId),
                    contentDescription = item.contentDescription,
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .clickable(
                            onClick = {
                                navController.navigate(
                                    Screens.CardDetail.withArgs(item.id)
                                )
                            }
                        )
                        .aspectRatio(8f / 9f)
                        .clip(MaterialTheme.shapes.extraLarge),
                    contentScale = ContentScale.Fit
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            repeat(items.size) { index ->
                val resId = if (index == pagerState.currentPage) {
                    R.drawable.ellipse1
                } else {
                    R.drawable.ellipse3
                }
                Image(
                    painter = painterResource(id = resId),
                    contentDescription = null,
                    modifier = Modifier.size(8.dp)
                )
            }
        }
    }
}