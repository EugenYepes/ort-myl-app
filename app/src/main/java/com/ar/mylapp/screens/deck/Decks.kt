package com.ar.mylapp.screens.deck

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.deck.DeckNameCard
import com.ar.mylapp.components.entryData.InputThree
import com.ar.mylapp.components.entryData.InputTwo
import com.ar.mylapp.components.title.Title1
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.viewmodel.DecksViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun DecksScreen(
    navController: NavController,
    topBarViewModel: TopBarViewModel,
    viewModel: DecksViewModel
){
    var showDialog by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("MIS MAZOS")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel.decks.forEach { deck ->
            DeckNameCard(
                title2 = deck.name,
                title3 = deck.cards.size.toString(),
                modifier = Modifier.clickable {
                    navController.navigate(Screens.DeckDetail.withArgs(deck.id))
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button1(
            onClick = { showDialog = true },
            text = "Nuevo Mazo"
        )

        if (showDialog) {
            CreateDeckPopup(
                onDismiss = { showDialog = false },
                onConfirm = { name, desc ->
                    viewModel.addDeck(name, desc)
                    showDialog = false
                }
            )
        }
    }
}

@Composable
fun CreateDeckPopup(
    onDismiss: () -> Unit,
    onConfirm: (String, String) -> Unit
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
                    Title1(title = "Nuevo Mazo")

                    InputTwo(
                        label = "Nombre",
                        initialValue = name,
                        onValueChange = { name = it }
                    )

                    InputThree(
                        label = "Descripcion",
                        initialValue = description,
                        onValueChange = { description = it }
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Button1(
                        onClick = {
                            if (name.isNotBlank()) {
                                onConfirm(name, description)
                            }
                        },
                        text = "Crear Mazo"
                    )
                }
            }
        }
    }
}