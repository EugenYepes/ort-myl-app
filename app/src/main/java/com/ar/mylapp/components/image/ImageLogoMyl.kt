package com.ar.mylapp.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R


@Composable
fun ImageLogoMyl(
    modifier: Modifier = Modifier,
    height: Dp = 300.dp
) {
    Image(
        painter = painterResource(id = R.drawable.myl_logo_image),
        contentDescription = "Logo de la aplicación",
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(height)
            .padding(horizontal = 12.dp, vertical = 3.dp)
    )
}