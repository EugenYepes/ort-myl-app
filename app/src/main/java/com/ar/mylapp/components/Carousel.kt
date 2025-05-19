package com.ar.mylapp.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun Carousel() {
    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        val contentDescription: String
    )

    val carouselItems = remember {
        listOf(
            CarouselItem(0, R.drawable.cardeolo, "card1"),
            CarouselItem(1, R.drawable.cardhelios, "card2"),
            CarouselItem(2, R.drawable.cardgaia, "card3")
        )
    }

    val selectedIndex = remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { carouselItems.count() },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(top = 16.dp, bottom = 16.dp),
            itemWidth = 230.dp,
            itemSpacing = 20.dp,
            contentPadding = PaddingValues(horizontal = 20.dp)
        ) { i ->
            val item = carouselItems[i]
            Image(
                modifier = Modifier
                    .height(330.dp)
                    .maskClip(MaterialTheme.shapes.extraLarge),
                painter = painterResource(id = item.imageResId),
                contentDescription = item.contentDescription,
                contentScale = ContentScale.Crop
            )
        }
        Box(
            Modifier
                .width(393.dp)
                .height(40.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Row (
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
            ){
                repeat(carouselItems.size) { index ->
                    val ellipse = if (index == selectedIndex.value) R.drawable.ellipse1 else R.drawable.ellipse3
                    Image(
                        painter = painterResource(id = ellipse),
                        contentDescription = null,
                        modifier = Modifier.size(8.dp)
                    )
                }
            }
        }
    }
}