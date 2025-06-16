package com.ar.mylapp.components.card

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ar.mylapp.components.number_picker.NumberPicker
import com.ar.mylapp.viewmodel.CardViewModel

@Composable
fun UserCardsPicker(
    cardId: Int,
    cardViewModel: CardViewModel,
    token: String?
) {
    var quantity by remember { mutableIntStateOf(0) }
    var initialQuantity by remember { mutableIntStateOf(0) }

    LaunchedEffect(cardId) {
        quantity = cardViewModel.getQuantityOfObtainedCard(cardId)
        initialQuantity = quantity
        token?.let {
            cardViewModel.resetAndLoadUserCards(it)
        }
    }

    LaunchedEffect(quantity) {
        if (quantity != initialQuantity && token != null) {
            val diff = quantity - initialQuantity
            if (diff > 0) {
                cardViewModel.addCardToUserCards(token, cardId, diff)
            } else {
                cardViewModel.removeCardFromUserCards(token, cardId, -diff)
            }
            initialQuantity = quantity
        }
    }

    Spacer(modifier = Modifier.size(22.dp))
    NumberPicker(
        number = quantity,
        min = 0,
        max = 10000,
        onValueChange = { newQty -> quantity = newQty }
    )
}