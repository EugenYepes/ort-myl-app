package com.ar.mylapp.components.popup

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.entryData.InputThree
import com.ar.mylapp.components.entryData.InputTwo
import com.ar.mylapp.components.title.Title1
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight

@Composable
fun CreateDeckPopup(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
) {
    var name by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .width(400.dp)
                .height(600.dp)
                .padding(16.dp)
                .border(2.dp, GoldLight, shape = RoundedCornerShape(12.dp))
        ) {
            Card(
                colors = CardColors(
                    containerColor = BlackLight,
                    contentColor = GoldDark,
                    disabledContentColor = GoldDark,
                    disabledContainerColor = BlackLight
                ),
                modifier = Modifier.fillMaxSize(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp).fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Title1(title = stringResource(R.string.new_deck))

                    InputTwo(
                        label = stringResource(R.string.deck_name),
                        initialValue = name,
                        onValueChange = { name = it }
                    )

                    InputThree(
                        label = stringResource(R.string.description),
                        initialValue = description,
                        onValueChange = { description = it }
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Button1(
                        onClick = {
                            if (name.isNotBlank()) {
                                onConfirm(name, description)
                            }
                        },
                        text = stringResource(R.string.create_deck)
                    )
                }
            }
        }
    }
}