package com.ar.mylapp.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ar.com.myldtos.cards.CardDTO
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.viewmodel.DecksViewModel

@Composable
fun CardDetailContent(
    card: CardDTO,
    showCardDetailPopup: Boolean,
    onShowInfo: () -> Unit,
    onAddToDeck: () -> Unit,
    onDismissPopup: () -> Unit,
    showAddToDeckPopup: Boolean,
    onDismissAddPopup: () -> Unit,
    onAddSuccess: () -> Unit,
    onDeleteSuccess: () -> Unit,
    decksViewModel: DecksViewModel,
    userAuthenticationViewModel: UserAuthenticationViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardDetailImage(card)
        Spacer(modifier = Modifier.size(22.dp))
        ShowButtons(
            onClickShowInfo = onShowInfo,
            onClickAddToDeck = onAddToDeck,
            userAuthenticationViewModel = userAuthenticationViewModel
        )
        if (showCardDetailPopup) {
            CardDetailPopup(
                onDismiss = onDismissPopup,
                card = card
            )
        }
        if (showAddToDeckPopup) {
            AddToDeckPopup(
                onDismiss = onDismissAddPopup,
                onAddSuccess = onAddSuccess,
                onDeleteSuccess = onDeleteSuccess,
                decksViewModel = decksViewModel,
                userAuthenticationViewModel = userAuthenticationViewModel,
                cardId = card.id
            )
        }
    }
}
