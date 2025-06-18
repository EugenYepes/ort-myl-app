package com.ar.mylapp.screens.store

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.viewmodel.StoreViewModel
import com.ar.mylapp.viewmodel.TopBarViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ar.mylapp.auth.UserAuthenticationViewModel
import com.ar.mylapp.components.store.StoreInfoCard
import com.ar.mylapp.navigation.Screens

@Composable
fun StoresScreen(
    navController: NavController,
    topBarViewModel: TopBarViewModel,
    storeViewModel: StoreViewModel,
    userAuthenticationViewModel: UserAuthenticationViewModel
){
    val stores by storeViewModel.stores.collectAsState()
    val title = stringResource(R.string.topbar_stores_title)
    LaunchedEffect(Unit) {
        storeViewModel.loadStores()
        topBarViewModel.setTopBar(title)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
    ) {
        items(stores) { store ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                StoreInfoCard(
                    title = store.name,
                    text = store.address,
                    storeUid = store.uuid,
                    phoneNumber = store.phoneNumber,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .width(366.dp),
                    onCardClick = {
                        navController.navigate("${Screens.StoreDetail.screen}/${store.uuid}")
                    },
                    userAuthenticationViewModel = userAuthenticationViewModel,
                    navController = navController
                )
            }
        }
    }
}