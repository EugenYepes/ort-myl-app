package com.ar.mylapp.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CardColors
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ar.mylapp.ui.theme.Red
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.window.Dialog
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.R
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.White

@Composable
fun CardDetailPopup(onDismiss: () -> Unit, card: CardDTO) {
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
            ShowInfo(card)
        }
    }
}

@Composable
fun ShowInfo(card: CardDTO) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        InfoRow(label = "Tipo", value = card.type?.name ?: "-")
        InfoRow(label = "Fuerza", value = card.damage?.toString() ?: "-")
        InfoRow(label = "Coste de Oro", value = card.cost?.toString() ?: "-")
        InfoRow(label = "Raza", value = card.race?.name ?: "-")
        InfoRow(label = "Rareza", value = card.rarity?.name ?: "-")
        Spacer(modifier = Modifier.size(24.dp))
        InfoAbility(label = "Habilidad", ability = card.description ?: "-")
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontWeight = FontWeight(400),
                color = White
            )
        )
        Text(
            text = value,
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 18.sp,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontWeight = FontWeight(400),
                color = White
            )
        )
    }
    HorizontalDivider(thickness = 2.dp, modifier = Modifier.padding(8.dp))
}

@Composable
fun InfoAbility(label: String, ability: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(GoldDark)
                .padding(vertical = 6.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
                .border(width = 2.dp, color = GoldDark)
        ) {
            Text(
                text = ability,
                color = White,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@Composable
fun CloseIcon(onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    IconButton(
        onClick = onDismiss,
        modifier = modifier.padding(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Close",
            tint = Red
        )
    }
}