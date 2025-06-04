package com.ar.mylapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.buttons.Button6
import com.ar.mylapp.components.card.CardCarousel
import com.ar.mylapp.components.title.Title2
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    userAuthenticationViewModel: UserAuthenticationViewModel,
    topBarViewModel: TopBarViewModel
){
    val title = stringResource(R.string.topbar_home_title)
    val subtitle = stringResource(R.string.topbar_home_subtitle)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title, subtitle)
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top  = 10.dp, start = 24.dp, end = 24.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Title2(
                title = "Acceso rápido",
                modifier = Modifier.align(Alignment.Start)
            )
            Row (
                horizontalArrangement = Arrangement.spacedBy(40.dp),
            ) {
                Column (
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Button6(
                        onClick = { navController.navigate(Screens.Cards.screen) },
                        text = "CARTAS",
                        icon =  painterResource(id = R.drawable.cards_icon)
                    )
                    Button6(
                        onClick = { navController.navigate(Screens.Decks.screen) },
                        text = "MAZOS",
                        icon =  painterResource(id = R.drawable.decks_icon)
                    )
                    Button6(
                        onClick = { navController.navigate(Screens.Account.screen) },
                        text = "CUENTA",
                        icon =  painterResource(id = R.drawable.account_icon)
                    )
                }
                Column (
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ){
                    Button6(
                        onClick = { navController.navigate(Screens.Stores.screen) },
                        text = "TIENDAS",
                        icon =  painterResource(id = R.drawable.store_icon)
                    )
                    Button6(
                        onClick = { navController.navigate(Screens.Guidebook.screen) },
                        text = "GUIA",
                        icon =  painterResource(id = R.drawable.guide_icon)
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Title2(
                title = "Cartas destacadas",
                modifier = Modifier.align(Alignment.Start)
            )
            CardCarousel()
        }
    }
}