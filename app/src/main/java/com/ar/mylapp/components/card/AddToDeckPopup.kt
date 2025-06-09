package com.ar.mylapp.components.card

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.White

@Composable
fun AddToDeckPopup(onDismiss: () -> Unit){
    Dialog(onDismissRequest = onDismiss) {
        Card(
            colors = CardColors(
                containerColor = BlackLight,
                contentColor = White,
                disabledContentColor = GoldDark,
                disabledContainerColor = BlackLight,
            ),
            modifier = Modifier
                .width(400.dp)
                .height(600.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            CloseIcon(onDismiss, modifier = Modifier.align(Alignment.End))
        }
    }
}