package com.ar.mylapp.components.popup

import android.util.Log
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.ar.mylapp.components.title.Title1
import com.ar.mylapp.models.cardProperties.DeckCardProperties
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.White
import com.ar.mylapp.viewmodel.DecksViewModel

@Composable
fun AddToDeckPopup(
    onDismiss: () -> Unit,
    onAddSuccess: () -> Unit,
    onDeleteSuccess: () -> Unit,
    onAddFail: () -> Unit,
    onDeleteFail: () -> Unit,
    decksViewModel: DecksViewModel,
    cardId: Int,
    userAuthenticationViewModel: UserAuthenticationViewModel
) {
    val currentQuantities = remember { mutableStateMapOf<Int, Int>() }
    val numbers = remember { mutableStateMapOf<Int, Int>() }
    val hasLoaded = remember { mutableStateMapOf<String, Boolean>() } // evita múltiple carga

    LaunchedEffect(key1 = true) {
        if (hasLoaded["$cardId"] == true) return@LaunchedEffect
        val updatedQuantities = decksViewModel.getCardQuantitiesForCard(cardId)
        currentQuantities.clear()
        currentQuantities.putAll(updatedQuantities)

        numbers.clear()
        decksViewModel.decks.value.forEach { deck ->
            numbers[deck.id] = updatedQuantities[deck.id] ?: 0
        }

        hasLoaded["$cardId"] = true
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
                    DeckQuantityRow(
                        deckName = deck.name,
                        quantity = numbers[deck.id] ?: 0,
                        onQuantityChange = { newValue -> numbers[deck.id] = newValue }
                    )
                }
            }

            Button1(
                onClick = {
                    val decksToAdd = mutableListOf<DeckCardProperties>()
                    val decksToRemove = mutableListOf<DeckCardProperties>()

                    numbers.forEach { (deckId, newQuantity) ->
                        val current = currentQuantities[deckId] ?: 0
                        val difference = newQuantity - current

                        if (difference > 0 && newQuantity <= 3) {
                            decksToAdd.add(DeckCardProperties(deckId, difference))
                        } else if (difference < 0) {
                            decksToRemove.add(DeckCardProperties(deckId, -difference))
                        }
                    }

                    val token = userAuthenticationViewModel.token ?: return@Button1

                    if (decksToRemove.isNotEmpty()) {
                        decksViewModel.deleteCardFromDeck(token, cardId, decksToRemove) { success ->
                            if (success) {
                                decksViewModel.loadDecks(token)
                                onDeleteSuccess()
                            } else {
                                onDeleteFail()
                            }
                        }
                    } else if (decksToAdd.isNotEmpty()) {
                        decksViewModel.addCardToDecks(token, cardId, decksToAdd) { success ->
                            if (success) {
                                onAddSuccess()
                            } else {
                                onAddFail()
                            }
                        }
                    }
                },
                text = stringResource(R.string.confirm)
            )
        }
    }
}
