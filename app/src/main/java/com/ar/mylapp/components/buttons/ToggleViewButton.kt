package com.ar.mylapp.components.buttons

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.GreenDark
import com.ar.mylapp.R

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun ToggleViewButton(
    isGridView: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val buttonSize = screenWidth * 0.13f
    Button(
        onClick = onClick,
        modifier = modifier
            .size(buttonSize)
            .border(
                width = 1.dp,
                color = GoldLight,
                shape = RoundedCornerShape(12.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = GreenDark,
            contentColor = GoldDark
        ),
        shape = RoundedCornerShape(12.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            painter = painterResource(
                id = if (isGridView) R.drawable.list_icon else R.drawable.grid_icon
            ),
            contentDescription = "Cambiar vista",
            modifier = Modifier.size(buttonSize * 0.5f)
        )
    }
}
