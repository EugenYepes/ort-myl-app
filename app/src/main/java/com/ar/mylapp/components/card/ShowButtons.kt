package com.ar.mylapp.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ar.mylapp.components.buttons.ButtonIcon
import com.ar.mylapp.R

@Composable
fun ShowButtons(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonIcon(onClick = onClick, image = painterResource(id = R.drawable.search))
        ButtonIcon(onClick = onClick, image = painterResource(id = R.drawable.folder_plus))
        ButtonIcon(onClick = onClick, image = painterResource(id = R.drawable.check_square))
    }
}