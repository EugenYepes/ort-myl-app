package com.ar.mylapp.screens.store

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ar.com.myldtos.users.StoreDTO
import android.net.Uri
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.WhatsAppButton
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.viewmodel.TopBarViewModel
import androidx.core.net.toUri
import com.ar.mylapp.navigation.normalizeUrl

@Composable
fun StoreDetailScreen(
    topBarViewModel: TopBarViewModel,
    store: StoreDTO
) {
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(store.name.uppercase())
    }

    val context = LocalContext.current

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

        if (!store.url.isNullOrBlank()) {
            val normalizedUrl = normalizeUrl(store.url)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.link_icon),
                    contentDescription = null,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text5(
                    text = store.url,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, normalizedUrl.toUri())
                        context.startActivity(intent)
                    }
                )
            }
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