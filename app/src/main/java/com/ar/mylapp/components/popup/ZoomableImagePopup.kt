package com.ar.mylapp.components.popup

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import coil3.compose.AsyncImage
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ZoomableImagePopupDialog(
    imageUrl: String,
    onDismiss: () -> Unit
) {
    var scale by remember { mutableFloatStateOf(1f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val imageWidth = screenWidth * 0.8f
    val imageHeight = imageWidth * 1.43f

    val imagePxWidth = with(LocalDensity.current) { imageWidth.toPx() }
    val imagePxHeight = with(LocalDensity.current) { imageHeight.toPx() }

    val state = rememberTransformableState { zoomChange, offsetChange, _ ->
        val newScale = (scale * zoomChange).coerceAtLeast(1f)
        scale = newScale

        if (newScale > 1f) {
            val newOffset = offset + offsetChange

            // Calcular el desplazamiento máximo permitido
            val maxX = (imagePxWidth * (newScale - 1)) / 2f
            val maxY = (imagePxHeight * (newScale - 1)) / 2f

            offset = Offset(
                x = newOffset.x.coerceIn(-maxX, maxX),
                y = newOffset.y.coerceIn(-maxY, maxY)
            )
        } else {
            offset = Offset.Zero
        }
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .size(width = imageWidth, height = imageHeight)
                .border(2.dp, GoldDark, shape = RoundedCornerShape(12.dp))
                .background(Black.copy(alpha = 0.95f), shape = RoundedCornerShape(12.dp))
                .clip(RoundedCornerShape(12.dp))
                .transformable(state = state)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onDoubleTap = {
                            scale = if (scale > 1f) 1f else 2f
                            offset = Offset.Zero
                        },
                        onTap = { onDismiss() }
                    )
                },
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale,
                        scaleY = scale,
                        translationX = offset.x,
                        translationY = offset.y
                    )
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )
        }
    }
}
