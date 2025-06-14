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
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.card.AddToDeckPopup
import com.ar.mylapp.components.card.CardDetailImage
import com.ar.mylapp.components.card.CardDetailPopup
import com.ar.mylapp.components.card.ShowButtons
import com.ar.mylapp.viewmodel.CardViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun CardDetail(
    id: Int,
    topBarViewModel: TopBarViewModel,
    viewModel: CardViewModel,
    userAuthenticationViewModel: UserAuthenticationViewModel
) {
    val card = viewModel.selectedCard
    val isLoading = viewModel.isCardDetailLoading
    val error = viewModel.cardDetailError

    LaunchedEffect(id) {
        viewModel.loadCardById(id)
    }

    val title = stringResource(R.string.topbar_cards_title)
    LaunchedEffect(card?.name) {
        card?.let {
            topBarViewModel.setTopBar(title, capitalizeTitle(it.name))
        }
    }

    var showCardDetailPopup by remember { mutableStateOf(false) }
    var showAddToDeckPopup by remember { mutableStateOf(false) }


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
                    CardDetailImage(
                        card,
                        Alignment.Center
                    )
                    Spacer(modifier = Modifier.size(22.dp))
                    ShowButtons(
                        onClickShowInfo = { showCardDetailPopup = true },
                        onClickAddToDeck = { showAddToDeckPopup = true },
                        userAuthenticationViewModel = userAuthenticationViewModel
                    )
                    if (showCardDetailPopup) {
                        CardDetailPopup(onDismiss = { showCardDetailPopup = false }, card)
                    }
                    if (showAddToDeckPopup) {
                        AddToDeckPopup(onDismiss = { showAddToDeckPopup = false })
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
