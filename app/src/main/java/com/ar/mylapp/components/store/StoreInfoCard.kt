package com.ar.mylapp.components.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.WhatsAppButton
import com.ar.mylapp.components.text.Text8
import com.ar.mylapp.components.title.Title2
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldDark

@Composable
fun StoreInfoCard(
    title: String,
    text: String,
    phoneNumber: String,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clickable { onCardClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlackLight)
                .border(width = 1.dp, color = GoldDark, shape = RoundedCornerShape(8.dp))
                .padding(vertical = 5.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Title2(title = title)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.location_on),
                    contentDescription = null,
                    contentScale = ContentScale.None
                )
                Text8(
                    text = text,
                    maxLines = 1
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                WhatsAppButton(
                    phoneNumber = phoneNumber,
                    enabled = !phoneNumber.isEmpty()
                )
            }
        }
    }
}