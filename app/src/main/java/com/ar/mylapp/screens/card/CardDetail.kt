package com.ar.mylapp.screens.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.card.CardDetailContent
import com.ar.mylapp.components.dialog.SuccessDialog
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.utils.SuccessDialogType
import com.ar.mylapp.utils.capitalizeTitle
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
    var showSuccessDialog by remember { mutableStateOf(false) }
    var successDialogType by remember { mutableStateOf<SuccessDialogType?>(null) }

    LaunchedEffect(id) { viewModel.loadCardById(id) }

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
                CardDetailContent(
                    card = card,
                    showCardDetailPopup = showCardDetailPopup,
                    onShowInfo = { showCardDetailPopup = true },
                    onAddToDeck = {
                        decksViewModel.selectedCardId = card.id
                        showAddToDeckPopup = true
                    },
                    onDismissPopup = { showCardDetailPopup = false },
                    showAddToDeckPopup = showAddToDeckPopup,
                    onDismissAddPopup = { showAddToDeckPopup = false },
                    onAddSuccess = {
                        showAddToDeckPopup = false
                        successDialogType = SuccessDialogType.ADD
                        showSuccessDialog = true
                    },
                    onDeleteSuccess = {
                        showAddToDeckPopup = false
                        successDialogType = SuccessDialogType.DELETE
                        showSuccessDialog = true
                    },
                    decksViewModel = decksViewModel,
                    userAuthenticationViewModel = userAuthenticationViewModel
                )
                if (showSuccessDialog && successDialogType != null) {
                    SuccessDialog(
                        type = successDialogType!!,
                        onDismiss = { showSuccessDialog = false },
                        onConfirm = {
                            showSuccessDialog = false
                            navController.navigate(Screens.Decks.screen)
                        }
                    )
                }
            }
        }
    }
}