package com.ar.mylapp.components.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ar.mylapp.R

@Composable
fun ImageBackground() {
    Image(
        painter = painterResource(id = R.drawable.background),
        contentDescription = stringResource(R.string.image_background),
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
}