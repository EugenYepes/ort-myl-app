package com.ar.mylapp.screens.guidebook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.ar.mylapp.components.accordion.GuidebookAccordion
import com.ar.mylapp.viewmodel.TopBarViewModel

@Composable
fun GuidebookScreen(
    navController: NavController,
    topBarViewModel: TopBarViewModel
){
    LaunchedEffect(Unit) {
        topBarViewModel.setTopBar("¿COMÓ JUGAR?")
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GuidebookAccordion()
        }
    }

}