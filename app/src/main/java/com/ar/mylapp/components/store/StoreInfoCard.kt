package com.ar.mylapp.components.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.Button2
import com.ar.mylapp.components.text.Text8
import com.ar.mylapp.components.tittle.Tittle2
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.BlackLight

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun StoreInfoCardPreview() {
    StoreInfoCard(
        title = "Tienda #1",
        text = "Av. Cabildo 1110",
        buttonText = "Comunicarme por WhatsApp"
    )
}

@Composable
fun StoreInfoCard(
    title: String,
    text: String,
    buttonText: String
) {
    Card(
        modifier = Modifier
            .border(width = 1.dp, color = GoldDark)
            .width(366.dp)
            .height(153.dp)
            .background(color = BlackLight),
        colors = CardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        )
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 5.dp, horizontal = 10.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            Tittle2(
                tittle = title
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.location_on),
                    contentDescription = null,
                    contentScale = ContentScale.None,
                )
                Text8(
                    text = text
                )
            }
            Button2(
                onClick = {},
                text = buttonText,
                modifier = Modifier
                    .align(Alignment.End)
                    .width(245.dp)
            )
        }
    }
}