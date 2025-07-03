package com.ar.mylapp.components.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button6
import com.ar.mylapp.viewmodel.AccountViewModel

@Composable
fun ShowButtons(
    onClickShowInfo: () -> Unit,
    onClickAddToDeck: () -> Unit,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    accountViewModel: AccountViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button6(
            onClick = onClickShowInfo,
            text = stringResource(R.string.more_info),
            icon = painterResource(id = R.drawable.search),
        )
        Button6(
            onClick = onClickAddToDeck,
            text = stringResource(R.string.add_to_decks),
            icon = painterResource(id = R.drawable.folder_plus),
            enabled = userAuthenticationViewModel.isLoggedIn() && accountViewModel.isPlayerUser()
        )
    }
}