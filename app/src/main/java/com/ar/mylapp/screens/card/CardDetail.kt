package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.popup.AddToDeckPopup
import com.ar.mylapp.components.card.CardDetailImage
import com.ar.mylapp.components.card.UserCardsPicker
import com.ar.mylapp.components.popup.CardDetailPopup
import com.ar.mylapp.components.card.ShowButtons
import com.ar.mylapp.components.dialog.ShowDialogCard
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.utils.DialogType
import com.ar.mylapp.utils.capitalizeTitle
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun CardDetail(
    id: Int,
    topBarViewModel: TopBarViewModel,
    cardViewModel: CardViewModel,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    decksViewModel: DecksViewModel,
    navController: NavController
) {
    val card = cardViewModel.selectedCard
    val isLoading = cardViewModel.isCardDetailLoading
    val error = cardViewModel.cardDetailError
    var showCardDetailPopup by remember { mutableStateOf(false) }
    var showAddToDeckPopup by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }
    var dialogType by remember { mutableStateOf<DialogType?>(null) }

    LaunchedEffect(id) { cardViewModel.loadCardById(id) }

    val title = stringResource(R.string.topbar_cards_title)
    LaunchedEffect(card?.name) {
        card?.let { topBarViewModel.setTopBar(title, capitalizeTitle(it.name)) }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> { CircularProgressIndicator(Modifier.align(Alignment.Center)) }
            error != null -> {
                Text(
                    text = "Error: $error",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            card != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CardDetailImage(card)
                    Spacer(modifier = Modifier.size(22.dp))
                    ShowButtons(
                        onClickShowInfo = { showCardDetailPopup = true },
                        onClickAddToDeck = {
                            card.let {
                                decksViewModel.selectedCardId = it.id
                                showAddToDeckPopup = true
                            }
                        },
                        userAuthenticationViewModel = userAuthenticationViewModel
                    )
                    UserCardsPicker(
                        cardId = card.id,
                        cardViewModel = cardViewModel,
                        token = userAuthenticationViewModel.token
                    )

                    if (showCardDetailPopup) {
                        CardDetailPopup(onDismiss = { showCardDetailPopup = false }, card)
                    }
                    if (showAddToDeckPopup) {
                        AddToDeckPopup(
                            onDismiss = { showAddToDeckPopup = false },
                            onAddSuccess = {
                                showAddToDeckPopup = false
                                showDialog = true
                                dialogType = DialogType.ADD_SUCCESS
                            },
                            onDeleteSuccess = {
                                showAddToDeckPopup = false
                                showDialog = true
                                dialogType = DialogType.DELETE_SUCCESS
                            },
                            onAddFail = {
                                showAddToDeckPopup = false
                                showDialog = true
                                dialogType = DialogType.ADD_FAIL
                            },
                            onDeleteFail = {
                                showAddToDeckPopup = false
                                showDialog = true
                                dialogType = DialogType.DELETE_FAIL
                            },
                            decksViewModel = decksViewModel,
                            userAuthenticationViewModel = userAuthenticationViewModel,
                            cardId = card.id
                        )
                    }
                    if (showDialog && dialogType != null) {
                        ShowDialogCard(
                            dialogType = dialogType,
                            onDismissRequest = { showDialog = false },
                            onConfirm = {
                                showDialog = false
                                navController.navigate(Screens.Decks.screen)
                            }
                        )
                    }
                }
            }
        }
    }
}