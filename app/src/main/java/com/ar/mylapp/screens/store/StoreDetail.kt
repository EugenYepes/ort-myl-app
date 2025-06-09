package com.ar.mylapp.screens.store

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.WhatsAppButton
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.viewmodel.TopBarViewModel
import users.StoreDTO

@Composable
fun StoreDetailScreen(
    topBarViewModel: TopBarViewModel,
    store: StoreDTO
) {
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(store.name)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.location_icon),
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text5(
                text = store.address,
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.phone_icon),
                contentDescription = null,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text5(
                text = store.phoneNumber,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            WhatsAppButton(
                phoneNumber = store.phoneNumber,
                enabled = !store.phoneNumber.isEmpty()
            )
        }
    }
}