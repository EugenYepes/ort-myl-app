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
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.popup.AddToDeckPopup
import com.ar.mylapp.components.card.CardDetailImage
import com.ar.mylapp.components.card.UserCardsPicker
import com.ar.mylapp.components.popup.CardDetailPopup
import com.ar.mylapp.components.card.ShowButtons
import com.ar.mylapp.components.dialog.DialogWithText
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.Red
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

private enum class DialogType { ADD_SUCCESS, ADD_FAIL, DELETE_SUCCESS, DELETE_FAIL }

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

    LaunchedEffect(id) {
        cardViewModel.loadCardById(id)
    }

    val title = stringResource(R.string.topbar_cards_title)
    LaunchedEffect(card?.name) {
        card?.let {
            topBarViewModel.setTopBar(title, capitalizeTitle(it.name))
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            isLoading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
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
                        val (title, message, borderColor) = when (dialogType) {
                            DialogType.ADD_SUCCESS ->
                                Triple(
                                    stringResource(R.string.success),
                                    stringResource(R.string.add_to_deck_success_msg),
                                    GoldDark
                                )

                            DialogType.DELETE_SUCCESS ->
                                Triple(
                                    stringResource(R.string.success),
                                    stringResource(R.string.delete_card_from_deck_success_msg),
                                    GoldDark
                                )

                            DialogType.ADD_FAIL ->
                                Triple(
                                    stringResource(R.string.fail),
                                    stringResource(R.string.add_to_deck_error_msg),
                                    Red
                                )

                            DialogType.DELETE_FAIL ->
                                Triple(
                                    stringResource(R.string.fail),
                                    stringResource(R.string.delete_card_from_deck_error_msg),
                                    Red
                                )

                            null ->
                                Triple("", "", GoldDark)
                        }
                        Dialog(onDismissRequest = { showDialog = false }) {
                            DialogWithText(
                                title = title,
                                text = message.toString(),
                                button7Text = stringResource(R.string.back),
                                button8Text = stringResource(R.string.go_to_decks),
                                onClick = { showDialog = false },
                                onConfirm = {
                                    showDialog = false
                                    navController.navigate(Screens.Decks.screen)
                                },
                                borderColor = borderColor
                            )
                        }
                    }
                }
            }
        }
    }
}

fun capitalizeTitle(input: String): String {
    val exceptions = setOf("de", "la", "el", "los", "las", "y", "en", "del")
    return input
        .split(" ")
        .mapIndexed { index, word ->
            if (word.lowercase() in exceptions && index != 0) {
                word.lowercase()
            } else {
                word.lowercase().replaceFirstChar { it.titlecase() }
            }
        }
        .joinToString(" ")
}