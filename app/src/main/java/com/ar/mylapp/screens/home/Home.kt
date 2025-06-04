package com.ar.mylapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    val buttons = listOf(
        Triple("CARTAS", R.drawable.cards_icon, Screens.Cards.screen),
        Triple("MAZOS", R.drawable.decks_icon, Screens.Decks.screen),
        Triple("CUENTA", R.drawable.account_icon, Screens.Account.screen),
        Triple("TIENDAS", R.drawable.store_icon, Screens.Stores.screen),
        Triple("GUÍA", R.drawable.guide_icon, Screens.Guidebook.screen),
    )

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
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(40.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                userScrollEnabled = false,
                content = {
                    items(buttons.size) { index ->
                        val (text, iconRes, screen) = buttons[index]
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button6(
                                onClick = { navController.navigate(screen) },
                                text = text,
                                icon = painterResource(id = iconRes)
                            )
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Title2(
                title = "Cartas destacadas",
                modifier = Modifier.align(Alignment.Start)
            )
            CardCarousel(navController)
        }
    }
}