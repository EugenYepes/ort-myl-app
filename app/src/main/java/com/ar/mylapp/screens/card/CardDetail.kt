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
import com.ar.mylapp.components.card.AddToDeckPopup
import com.ar.mylapp.components.card.CardDetailImage
import com.ar.mylapp.components.card.CardDetailPopup
import com.ar.mylapp.components.card.ShowButtons
import com.ar.mylapp.components.dialog.DialogWithText
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun CardDetail(
    id: Int,
    topBarViewModel: TopBarViewModel,
    viewModel: CardViewModel,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    decksViewModel: DecksViewModel,
    navController: NavController
) {
    val card = viewModel.selectedCard
    val isLoading = viewModel.isCardDetailLoading
    val error = viewModel.cardDetailError
    var showCardDetailPopup by remember { mutableStateOf(false) }
    var showAddToDeckPopup by remember { mutableStateOf(false) }
    var showAddSuccessDialog by remember { mutableStateOf(false) }
    var showDeleteSuccessDialog by remember { mutableStateOf(false) }

    LaunchedEffect(id) {
        viewModel.loadCardById(id)
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
                androidx.compose.material3.Text(
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
                    if (showCardDetailPopup) {
                        CardDetailPopup(onDismiss = { showCardDetailPopup = false }, card)
                    }
                    if (showAddToDeckPopup) {
                        AddToDeckPopup(
                            onDismiss = { showAddToDeckPopup = false },
                            onAddSuccess = {
                                showAddToDeckPopup = false
                                showAddSuccessDialog = true
                            },
                            onDeleteSuccess = {
                                showAddToDeckPopup = false
                                showDeleteSuccessDialog = true
                            },
                            decksViewModel = decksViewModel,
                            userAuthenticationViewModel = userAuthenticationViewModel,
                            cardId = card.id
                        )
                    }
                    if (showAddSuccessDialog) {
                        Dialog(onDismissRequest = { showAddSuccessDialog = false }) {
                            DialogWithText(
                                title = stringResource(R.string.success),
                                text = stringResource(R.string.add_to_deck_success_msg),
                                button7Text = stringResource(R.string.back),
                                button8Text = stringResource(R.string.go_to_decks),
                                onClick = {
                                    showAddSuccessDialog = false
                                },
                                onConfirm = {
                                    navController.navigate(Screens.Decks.screen)
                                }
                            )
                        }
                    }
                    if (showDeleteSuccessDialog) {
                        Dialog(onDismissRequest = { showAddSuccessDialog = false }) {
                            DialogWithText(
                                title = stringResource(R.string.success),
                                text = stringResource(R.string.delete_card_from_deck_success_msg),
                                button7Text = stringResource(R.string.back),
                                button8Text = "Ir a mazos",
                                onClick = {
                                    showAddSuccessDialog = false
                                },
                                onConfirm = {
                                    navController.navigate(Screens.Decks.screen)
                                }
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