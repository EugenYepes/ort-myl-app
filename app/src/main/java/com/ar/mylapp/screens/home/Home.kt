package com.ar.mylapp.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.ButtonsGrid
import com.ar.mylapp.components.card.CardCarousel
import com.ar.mylapp.components.title.Title2
import com.ar.mylapp.navigation.getHomeButtonsGridInfo
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    topBarViewModel: TopBarViewModel
){
    val title = stringResource(R.string.topbar_home_title)
    val subtitle = stringResource(R.string.topbar_home_subtitle)
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar(title, subtitle)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 10.dp, start = 24.dp, end = 24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Title2(
                title = stringResource(R.string.quick_access),
                modifier = Modifier.align(Alignment.Start)
            )
            ButtonsGrid(getHomeButtonsGridInfo(), navController)
            Spacer(modifier = Modifier.height(10.dp))
            Title2(
                title = stringResource(R.string.cards_grid),
                modifier = Modifier.align(Alignment.Start)
            )
            CardCarousel(navController)
        }
    }
}