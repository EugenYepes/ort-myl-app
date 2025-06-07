package com.ar.mylapp.components.buttons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ButtonsGrid(
    buttonGridInfo: List<Triple<String, Int, String>>,
    navController: NavController
){
    for (i in buttonGridInfo.indices step 2) {
        if (i + 1 < buttonGridInfo.size) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(40.dp)
            ) {
                Button6(
                    onClick = { navController.navigate(buttonGridInfo[i].third) {
                        launchSingleTop = true
                    } },
                    text = buttonGridInfo[i].first,
                    icon = painterResource(id = buttonGridInfo[i].second),
                )
                Button6(
                    onClick = { navController.navigate(buttonGridInfo[i + 1].third) {
                        launchSingleTop = true
                    } },
                    text = buttonGridInfo[i + 1].first,
                    icon = painterResource(id = buttonGridInfo[i + 1].second),
                )
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button6(
                    onClick = { navController.navigate(buttonGridInfo[i].third) {
                        launchSingleTop = true
                    } },
                    text = buttonGridInfo[i].first,
                    icon = painterResource(id = buttonGridInfo[i].second),
                )
            }
        }
    }
}