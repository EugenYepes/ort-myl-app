package com.ar.mylapp.screens.welcome.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.components.buttons.Button1
import com.ar.mylapp.components.image.ImageLogoMyl
import com.ar.mylapp.components.text.Text5
import com.ar.mylapp.components.title.Title2
import com.ar.mylapp.navigation.Screens

@Composable
fun ConfirmRegister(
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 2.dp, vertical = 24.dp)
                .align(Alignment.TopCenter)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            ImageLogoMyl(modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(32.dp))

            Title2(title = stringResource(R.string.verify_email_title))

            Spacer(modifier = Modifier.height(12.dp))

            Text5(
                text = stringResource(R.string.verify_email_subtitle),
                modifier = Modifier.padding(horizontal = 8.dp),
            )

            Spacer(modifier = Modifier.height(36.dp))

            Button1(
                text = stringResource(R.string.login_mayus),
                onClick = { navController.navigate(Screens.Login.screen) }
            )
        }
    }
}