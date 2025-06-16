package com.ar.mylapp.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ar.mylapp.components.buttons.Button8
import com.ar.mylapp.components.title.Title5
import com.ar.mylapp.ui.theme.Black
import com.ar.mylapp.ui.theme.GoldDark

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun DialogWithoutTextPreview() {
    DialogWithoutText(
        title = "Title",
        button8Text = "Label",
        onClick = {}
    )
}

@Composable
fun DialogWithoutText(
    title: String,
    button8Text: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .border(width = 1.dp, color = GoldDark, shape = RoundedCornerShape(20.dp))
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0x47000000),
                ambientColor = Color(0x47000000)
            )
            .width(385.dp)
            .height(140.dp)
            .background(color = Black, shape = RoundedCornerShape(20.dp))
            .padding(24.dp),
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
            Title5(
                title = title
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.Top,
            ) {
                Button8(
                    onClick = onClick,
                    text = button8Text
                )
            }
        }
    }
}