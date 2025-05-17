package com.ar.mylapp.components.bottonBar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ar.mylapp.R
import com.ar.mylapp.navigation.getBottomMenuContent
import com.ar.mylapp.ui.theme.GoldLight
import com.ar.mylapp.ui.theme.Gray
import com.ar.mylapp.viewmodel.BottomBarViewModel

@Composable
fun MyBottomAppBar(
    navController: NavController,
    bottomBarViewModel: BottomBarViewModel
) {
    val navItems = getBottomMenuContent()
    val currentSection = bottomBarViewModel.currentSection.value

    BottomAppBar(
        containerColor = Color.Transparent,
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            navItems.forEach { item ->
                IconButton(
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = if (currentSection == item.section) GoldLight else Gray
                        )
                        Text(
                            text = item.label,
                            fontFamily = FontFamily(Font(R.font.patua_one_regular)),
                            color = if (currentSection == item.section) GoldLight else Gray,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}