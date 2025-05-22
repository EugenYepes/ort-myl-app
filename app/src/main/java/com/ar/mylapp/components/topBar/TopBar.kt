package com.ar.mylapp.components.topBar

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ar.mylapp.viewmodel.TopBarViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ar.mylapp.R
import com.ar.mylapp.navigation.Screens
import com.ar.mylapp.ui.theme.BlackLight
import com.ar.mylapp.ui.theme.GoldBeige
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.White

@Composable
fun MyTopBar(
    topBarViewModel: TopBarViewModel,
    navController: NavController,
) {
    val title by topBarViewModel.title
    val subtitle by topBarViewModel.subtitle
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()

    Surface(
        color = BlackLight,
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, GoldLight),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = statusBarPadding.calculateTopPadding(), start = 8.dp, end = 8.dp, bottom = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            // Back Icon
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.back_icon),
                    contentDescription = "Volver",
                    tint = White
                )
            }

            // Texts
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(
                        horizontal = 6.dp,
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                        fontWeight = FontWeight(400),
                        color = GoldLight
                    )
                )
                subtitle?.let {
                    Text(
                        text = it,
                        style = TextStyle(
                            fontSize = 13.sp,
                            lineHeight = 24.sp,
                            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                            fontWeight = FontWeight(400),
                            color = GoldBeige
                        )
                    )
                }
            }

            // Account icon
            IconButton(
                onClick = { navController.navigate(Screens.Account.screen) })
            {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.account_icon),
                    contentDescription = "Cuenta",
                    tint = White
                )
            }
        }
    }
}


@Preview
@Composable
private fun MyTopBarPreview(){
    val topBarViewModel: TopBarViewModel = viewModel()
    val navController = rememberNavController()
    MyTopBar(
        topBarViewModel = topBarViewModel,
        navController = navController
    )
}