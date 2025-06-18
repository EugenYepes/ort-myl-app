package com.ar.mylapp.screens.deck

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button2
import com.ar.mylapp.components.buttons.Button3
import com.ar.mylapp.components.buttons.Button4
import com.ar.mylapp.components.card.CardGrid
import com.ar.mylapp.components.dialog.DeleteDeckConfirmationDialog
import com.ar.mylapp.components.dialog.EditDeckConfirmationDialog
import com.ar.mylapp.components.popup.EditDeckPopup
import com.ar.mylapp.components.text.Text3
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@SuppressLint("ConfigurationScreenWidthHeight")
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

    val configuration = LocalConfiguration.current
    val screenHeightDp = configuration.screenHeightDp
    val buttonTextSize = if (screenHeightDp < 700) 16.sp else 20.sp

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
    if (showDeleteDialog) {
        DeleteDeckConfirmationDialog(
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if(deck.description.isNotBlank()){
            Text3(
                text = deck.description,
                fontSize = 14.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier.fillMaxWidth(),
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button3(
                onClick = { showDialog = true },
                text = stringResource(R.string.edit_deck),
                buttonTextSize = buttonTextSize,
                modifier = Modifier.weight(1f)
            )
            Button4(
                onClick = { showDeleteDialog = true },
                text = stringResource(R.string.delete_deck),
                buttonTextSize = buttonTextSize,
                modifier = Modifier.weight(1f)
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
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