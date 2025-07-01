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
import com.ar.mylapp.components.buttons.Button6

@Composable
fun ShowButtons(
    onClickShowInfo: () -> Unit,
    onClickAddToDeck: () -> Unit,
    userAuthenticationViewModel: UserAuthenticationViewModel
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button6(
            onClick = onClickShowInfo,
            text = "Ver mas Info.",
            icon = painterResource(id = R.drawable.search),
        )
        Button6(
            onClick = onClickAddToDeck,
            text = "Agregar a Mazos",
            icon = painterResource(id = R.drawable.folder_plus),
            enabled = userAuthenticationViewModel.isLoggedIn()
        )
    }
}