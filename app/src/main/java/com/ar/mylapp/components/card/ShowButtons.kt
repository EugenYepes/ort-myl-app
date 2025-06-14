package com.ar.mylapp.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ar.mylapp.components.buttons.ButtonIcon
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel

@Composable
fun ShowButtons(
    onClickShowInfo: () -> Unit,
    onClickAddToDeck: () -> Unit,
    userAuthenticationViewModel: UserAuthenticationViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonIcon(
            onClick = onClickShowInfo,
            image = painterResource(id = R.drawable.search)
        )
        ButtonIcon(
            onClick = onClickAddToDeck, 
            image = painterResource(id = R.drawable.folder_plus),
            enabled = userAuthenticationViewModel.isLoggedIn()
        )
    }
}