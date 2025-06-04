package com.ar.mylapp.components.card

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCarousel(navController: NavController) {

    data class CarouselItem(
        val id: Int,
        @DrawableRes val imageResId: Int,
        val contentDescription: String
    )

    val carouselItems = remember {
        listOf(
            CarouselItem(13696, R.drawable.cardeolo, "cardeolo"),
            CarouselItem(1665, R.drawable.cardhelios, "cardhelios"),
            CarouselItem(14691, R.drawable.cardgaia, "cardgaia")
        )
    }

    val initialIndex = carouselItems.size / 2
    val carouselState = rememberCarouselState(
        initialItem = initialIndex,
        itemCount = { carouselItems.size }
    )

    val selectedIndex = remember { mutableIntStateOf(initialIndex) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        HorizontalUncontainedCarousel(
            state = carouselState,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 10.dp),
            itemWidth = 230.dp,
            itemSpacing = 10.dp,
            contentPadding = PaddingValues(horizontal = 10.dp)
        ) { i ->
            val item = carouselItems[i]
            Image(
                painter = painterResource(id = item.imageResId),
                contentDescription = item.contentDescription,
                modifier = Modifier
                    .height(300.dp)
                    .maskClip(MaterialTheme.shapes.extraLarge)
                    .clickable {
                        navController.navigate("cardDetail/${item.id}")
                    },
                contentScale = ContentScale.Crop
            )
        }

//        Box(
//            Modifier
//                .fillMaxWidth()
//                .height(40.dp)
//                .padding(16.dp),
//            contentAlignment = Alignment.Center
//        ) {
//            Row(
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
//                repeat(carouselItems.size) { index ->
//                    val ellipse =
//                        if (index == selectedIndex.intValue) R.drawable.ellipse1 else R.drawable.ellipse3
//                    Image(
//                        painter = painterResource(id = ellipse),
//                        contentDescription = null,
//                        modifier = Modifier.size(8.dp)
//                    )
//                }
//            }
//        }
    }
}