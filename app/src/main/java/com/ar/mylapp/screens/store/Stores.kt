package com.ar.mylapp.screens.store

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.components.store.StoreInfoCard
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun StoresScreen(
    navController: NavController,
    topBarViewModel: TopBarViewModel
){
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("TIENDAS")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(30.dp, Alignment.Top),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxSize()
        ) {
            items(10) { index ->
                StoreInfoCard(
                    title = "Tienda #$index",
                    text = "Av. Cabildo 1110",
                    onClick = { navController.navigate(Screens.StoreDetail.screen) }
                )
            }
        }
    }
}