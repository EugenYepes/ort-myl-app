package com.ar.mylapp.components.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.GoldDark

@Preview
@Composable
fun ButtonIconPreview() {
    ButtonIcon(
        onClick = {},
        image = painterResource(id = R.drawable.folder_plus),
    )
}

@Composable
fun ButtonIcon(
    onClick: () -> Unit,
    image: Painter
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(width = 60.dp, height = 60.dp)
            .clip(RoundedCornerShape(50.dp))
            .background(color = GoldDark)
    )
    {
        Image(
            painter = image,
            contentDescription = "image description",
            modifier = Modifier.size(42.dp)
        )
    }
}