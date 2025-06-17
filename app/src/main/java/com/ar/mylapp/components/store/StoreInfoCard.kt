package com.ar.mylapp.components.store

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button4
import com.ar.mylapp.components.buttons.WhatsAppButton2
import com.ar.mylapp.components.popup.DeleteStorePopup
import com.ar.mylapp.components.text.Text8
import com.ar.mylapp.components.title.Title2
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldDark
import com.ar.mylapp.ui.theme.GreenDark

@Composable
fun StoreInfoCard(
    title: String,
    text: String,
    storeUid: String,
    phoneNumber: String,
    modifier: Modifier = Modifier,
    onCardClick: () -> Unit,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    navController: NavController
) {
    var showDeleteStorePopup by remember { mutableStateOf(false) }

    if (showDeleteStorePopup) {
        DeleteStorePopup(
            onDismiss = { showDeleteStorePopup = false },
            onConfirm = {
                userAuthenticationViewModel.invalidateStore(
                    storeUid = storeUid,
                    onSuccess = {
                        showDeleteStorePopup = false
                        navController.navigate(Screens.Stores.screen)
                    },
                    onError = { errorMsg ->
                        showDeleteStorePopup = false
                        Log.e("InvalidateStore", "Error: $errorMsg")
                    }
                )
            }
        )
    }

    Card(
        modifier = modifier
            .clickable { onCardClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(BlackLight)
                .border(width = 1.dp, color = GoldDark, shape = RoundedCornerShape(8.dp))
                .padding(vertical = 5.dp, horizontal = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Title2(title = title)

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.location_on),
                    contentDescription = null,
                    contentScale = ContentScale.None
                )
                Text8(
                    text = text,
                    maxLines = 1
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (userAuthenticationViewModel.isAdmin) Arrangement.SpaceBetween else Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (userAuthenticationViewModel.isAdmin) {
                    Button4(
                        text = stringResource(R.string.delete_store),
                        onClick = { showDeleteStorePopup = true }
                    )
                }
                WhatsAppButton2(
                    modifier = Modifier.padding(bottom = 10.dp),
                    phoneNumber = phoneNumber,
                    enabled = !phoneNumber.isEmpty(),
                    backgroundColor = GreenDark,
                    borderColor = GoldDark
                )
            }
        }
    }
}