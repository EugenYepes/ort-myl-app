package com.ar.mylapp.components.card

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.number_picker.NumberPicker
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.components.title.Title1
import com.ar.mylapp.models.cardProperties.DeckCardProperties
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.White
import com.ar.mylapp.viewmodel.DecksViewModel

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddToDeckPopup(
    onDismiss: () -> Unit,
    decksViewModel: DecksViewModel,
    cardId: Int,
    userAuthenticationViewModel: UserAuthenticationViewModel
){
    val currentQuantities = decksViewModel.getCardQuantitiesForCard(cardId)
    val numbers = remember {
        mutableStateMapOf<Int, Int>().apply {
            decksViewModel.decks.value.forEach { deck ->
                this[deck.id] = currentQuantities[deck.id] ?: 0
            }
        }
    }

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

            Title1(
                title = stringResource(R.string.add_to_deck),
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(bottom = 16.dp)
            ) {
                items(decksViewModel.decks.value.size) { index ->
                    val deck = decksViewModel.decks.value[index]
                    val number = numbers[deck.id] ?: 0
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text5(
                            text = deck.name,
                            modifier = Modifier.weight(1f)
                        )
                        NumberPicker(
                            modifier = Modifier.width(120.dp),
                            number = number,
                            onValueChange = { newValue ->
                                numbers[deck.id] = newValue
                            }
                        )
                    }
                }
            }
            Button1(
                onClick = {
                    val decksToAdd = numbers.mapNotNull { (deckId, newQuantity) ->
                        val current = currentQuantities[deckId] ?: 0
                        val difference = newQuantity - current

                        // Solo agregar si se quiere aumentar y no se pasa de 3
                        if (difference > 0 && current + difference <= 3) {
                            DeckCardProperties(deckId, difference)
                        } else {
                            null
                        }
                    }

                    if (decksToAdd.isNotEmpty()) {
                        decksViewModel.addCardToDecks(
                            token = userAuthenticationViewModel.token.toString(),
                            cardId = decksViewModel.selectedCardId ?: return@Button1,
                            deckList = decksToAdd
                        ) { success ->
                            if (success) {
                                onDismiss()
                            } else {
                                println("Error al agregar la carta a los mazos")
                            }
                        }
                    }
                },
                text = stringResource(R.string.confirm)
            )
        }
    }
}