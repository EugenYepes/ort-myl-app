package com.ar.mylapp.screens.deck

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.buttons.Button2
import com.ar.mylapp.components.buttons.Button3
import com.ar.mylapp.components.buttons.Button4
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.dialog.DialogWithText
import com.ar.mylapp.components.dialog.DialogWithoutText
import com.ar.mylapp.components.entryData.InputThree
import com.ar.mylapp.components.entryData.InputTwo
import com.ar.mylapp.components.text.Text3
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.components.title.Title1
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun DeckDetailScreen(
    deckId: Int,
    topBarViewModel: TopBarViewModel,
    decksViewModel: DecksViewModel,
    authViewModel: UserAuthenticationViewModel,
    navController: NavController
) {
    var showDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) }
    val deck = decksViewModel.decks.value.find { it.id == deckId }

    val title = stringResource(R.string.topbar_deck_details)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title, deck?.name)
    }

    if (deck == null) {
        Text5(
            text = stringResource(R.string.deck_not_found),
            modifier = Modifier.padding(24.dp),
        )
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text3(
            text = if (deck.description.isNotBlank()) deck.description else stringResource(R.string.no_description),
            fontSize = 14.sp,
            textAlign = TextAlign.Justify,
            modifier = Modifier.fillMaxWidth(),
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Button3(
                onClick = { showDialog = true },
                text = stringResource(R.string.edit_deck)
            )

            if (showDialog) {
                EditDeckPopup(
                    id = deck.id,
                    currentName = deck.name,
                    onDismiss = { showDialog = false },
                    onConfirm = { id, name, description ->
                        authViewModel.token?.let { token ->
                            decksViewModel.editDeck(token, id, name, description)
                        }
                        showDialog = false
                        showSuccessDialog = true
                    }
                )
            }
            if (showSuccessDialog) {
                EditDeckConfirmationDialog(
                    title = stringResource(R.string.edit_success_title),
                    button8Text = stringResource(R.string.edit_success_button),
                    onDismiss = { showSuccessDialog = false }
                )
            }
            Button4(
                onClick = { showDeleteDialog = true },
                text = stringResource(R.string.delete_deck)
            )
            if (showDeleteDialog) {
                DeleteDeckConfirmationDialog (
                    id = deck.id,
                    title = stringResource(R.string.delete_deck_title),
                    text = stringResource(R.string.delete_deck_text),
                    button7Text = stringResource(R.string.cancel),
                    button8Text = stringResource(R.string.delete),
                    onDismiss = { showDeleteDialog = false },
                    onConfirm = { id ->
                        authViewModel.token?.let {
                            decksViewModel.deleteDeck(
                                id = id,
                                token = it
                            ) { success ->
                                if (success) {
                                    navController.popBackStack()
                                }
                            }
                        }
                        showDeleteDialog = false
                    }
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth()
            ) {
                if (deck.cards.isNotEmpty()) {
                    CardGrid(
                        navController = navController,
                        cards = deck.cards.flatMap { deckCard ->
                            List(deckCard.quantity) { deckCard.card }
                        },
                        enablePagination = false
                    )
                } else {
                    Text5(
                        text = stringResource(R.string.no_cards),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button2(
                    onClick = { navController.navigate(Screens.Cards.screen) },
                    text = stringResource(R.string.add_cards_to_deck)
                )
            }
        }
    }
}

@Composable
fun EditDeckPopup(
    id : Int,
    currentName: String,
    onDismiss: () -> Unit,
    onConfirm: (Int, String, String) -> Unit
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
                modifier = Modifier
                    .fillMaxSize(),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    Title1(title = stringResource(R.string.edit_deck))

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
                            val finalName = if (name.isBlank()) currentName else name
                            onConfirm(id, finalName, description)
                        },
                        text = stringResource(R.string.edit_deck)
                    )
                }
            }
        }
    }
}

@Composable
fun EditDeckConfirmationDialog(
    title: String,
    button8Text: String,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        DialogWithoutText(
            title = title,
            button8Text = button8Text,
            onClick = onDismiss
        )
    }
}

@Composable
fun DeleteDeckConfirmationDialog(
    id : Int,
    title: String,
    text: String,
    button7Text: String,
    button8Text: String,
    onDismiss: () -> Unit,
    onConfirm: (Int) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        DialogWithText(
            title = title,
            text = text,
            button7Text = button7Text,
            button8Text = button8Text,
            onClick = onDismiss,
            onConfirm = { onConfirm(id) }
        )
    }
}